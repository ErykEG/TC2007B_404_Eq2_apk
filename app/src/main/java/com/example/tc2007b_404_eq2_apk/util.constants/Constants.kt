package com.example.tc2007b_404_eq2_apk.util.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    //Data Store Name
    const val DATASTORE = "my_datastore"

    // Keys to store values in data store
    val TOKEN = stringPreferencesKey("TOKEN")
    val ISADMIN = booleanPreferencesKey("ISADMIN")

    val avisoDePrivacidad = """
AVISO DE PRIVACIDAD

FRISA respeta su privacidad y se compromete a proteger sus datos personales. Este aviso de privacidad le informa sobre nuestras prácticas de recopilación, uso, divulgación y protección de su información personal.

1. Información que recopilamos:
   - Nombre, número de teléfono y dirección de correo electrónico.
   - Información de pago (por ejemplo, datos de tarjeta de crédito) cuando realiza una transacción con nosotros.
   - Datos de registro y uso cuando interactúa con nuestros servicios en línea.
   - Información demográfica como edad, género y preferencias.

2. Uso de su información:
   - Utilizamos su información para procesar pedidos, brindarle servicios y responder a sus consultas.
   - Podemos utilizar su información para enviarle correos electrónicos promocionales o boletines informativos (si ha dado su consentimiento).
   - Mejoramos nuestros productos y servicios mediante el análisis de datos y la retroalimentación de los usuarios.

3. Divulgación de información:
   - Compartimos su información con terceros solo cuando sea necesario para brindarle nuestros servicios o cumplir con requisitos legales.
   - No vendemos, alquilamos ni compartimos su información personal con terceros con fines de marketing sin su consentimiento.

4. Seguridad de datos:
   - Tomamos medidas razonables para proteger su información personal contra accesos no autorizados o divulgación.

5. Sus derechos:
   - Usted tiene derecho a acceder, corregir o eliminar su información personal.
   - Puede optar por no recibir correos electrónicos promocionales en cualquier momento.

6. Cambios a este aviso:
   - Podemos actualizar este aviso de privacidad ocasionalmente. La versión más reciente estará disponible en nuestro sitio web.

Si tiene alguna pregunta o inquietud sobre nuestro aviso de privacidad, comuníquese con nosotros a través de https://www.frisa.com/es/contact-us/contact-us

Fecha de entrada en vigor: 18/10/2023

FRISA
"""


}