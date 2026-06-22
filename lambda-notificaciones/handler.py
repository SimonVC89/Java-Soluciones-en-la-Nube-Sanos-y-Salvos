import json
import os
import urllib.request

MS_NOTIFICACIONES_URL = os.environ.get('MS_NOTIFICACIONES_URL', '')

def lambda_handler(event, context):
    print(f"Mensajes recibidos desde SQS: {len(event['Records'])}")

    for record in event['Records']:
        try:
            body = json.loads(record['body'])
            print(f"Procesando reporte ID={body.get('reporteId')} tipo={body.get('tipoReporte')}")

            tipo   = body.get('tipoReporte', 'PERDIDO')
            nombre = body.get('nombreMascota', 'Sin nombre')
            accion = 'perdida' if tipo == 'PERDIDO' else 'encontrada'

            notificacion = {
                "usuarioId": int(body.get('usuarioId', 1)),
                "mensaje":   f"Alerta: mascota {accion} — {nombre}. {body.get('descripcion', '')}".strip(),
                "tipo":      "PUSH",
                "reporteId": body.get('reporteId')
            }

            if not MS_NOTIFICACIONES_URL:
                print(f"[Lambda] MS_NOTIFICACIONES_URL no configurada. Notificacion generada: {notificacion}")
                continue

            data = json.dumps(notificacion).encode('utf-8')
            req  = urllib.request.Request(
                f"{MS_NOTIFICACIONES_URL}/api/notificaciones",
                data=data,
                headers={'Content-Type': 'application/json'},
                method='POST'
            )

            with urllib.request.urlopen(req, timeout=10) as response:
                resultado = response.read().decode('utf-8')
                print(f"[Lambda] Notificacion creada exitosamente: {resultado}")

        except Exception as e:
            print(f"[Lambda] Error procesando mensaje: {e}")

    return {"statusCode": 200, "body": "Mensajes procesados"}
