# Management CafeLab - API Reference
Referencia de endpoints del microservicio de gestión de inventario, lotes de café, Roast Profiles
## Overview

## Overview

- Microservicio: `management`
- Base path real: `/api/v1`
- Modulos documentados: `Coffee Lots`, `Inventory Entries`, `Suppliers` y `Roast Profiles`
- Formato de respuestas: `application/json`
- Swagger UI disponible en: `/swagger-ui/index.html`
- OpenAPI JSON disponible en: `/v3/api-docs`

## Reglas Generales

- Todos los endpoints exigen JWT Bearer a traves del ApiGateway.
- Los endpoints con body esperan `Content-Type: application/json`.
- Los errores controlados suelen responder con `{"message":"..."}`.

## Indice de Endpoints

| Módulo | Operación | Método | Ruta |
| ----- | ----- | ----- | ----- |
| Coffee Lots | Get coffee lot by id | `GET` | `/api/v1/coffee-lots/{coffeeLotId}` |
| Coffee Lots | Update coffee lot | `PUT` | `/api/v1/coffee-lots/{coffeeLotId}` |
| Coffee Lots | Delete coffee lot | `DELETE` | `/api/v1/coffee-lots/{coffeeLotId}` |
| Coffee Lots | Get all coffee lots | `GET` | `/api/v1/coffee-lots` |
| Coffee Lots | Create coffee lot | `POST` | `/api/v1/coffee-lots` |
| Coffee Lots | Update coffee lot status | `PATCH` | `/api/v1/coffee-lots/{coffeeLotId}/status` |
| Coffee Lots | Consume coffee lot stock | `PATCH` | `/api/v1/coffee-lots/{coffeeLotId}/consume-stock` |
| Coffee Lots | Get coffee lots by user | `GET` | `/api/v1/coffee-lots/user/{userId}` |
| Coffee Lots | Get coffee lots by supplier | `GET` | `/api/v1/coffee-lots/supplier/{supplierId}` |
| Coffee Lots | Get coffee lots by status | `GET` | `/api/v1/coffee-lots/status/{status}` |
| Coffee Lots | Get coffee lots by processing method | `GET` | `/api/v1/coffee-lots/processing-method/{processingMethod}` |
| Coffee Lots | Get coffee lots by origin | `GET` | `/api/v1/coffee-lots/origin/{origin}` |
| Coffee Lots | Get depleted coffee lots | `GET` | `/api/v1/coffee-lots/depleted` |
| Coffee Lots | Get coffee lots by coffee type | `GET` | `/api/v1/coffee-lots/coffee-type/{coffeeType}` |
| Coffee Lots | Get available coffee lots | `GET` | `/api/v1/coffee-lots/available` |
| Inventory Entries | Get inventory entries by user | `GET` | `/api/v1/inventory-entries/user/{userId}` |
| Suppliers | Get supplier by id | `GET` | `/api/v1/suppliers/{supplierId}` |
| Suppliers | Update supplier | `PUT` | `/api/v1/suppliers/{supplierId}` |
| Suppliers | Delete supplier | `DELETE` | `/api/v1/suppliers/{supplierId}` |
| Suppliers | Get all suppliers | `GET` | `/api/v1/suppliers` |
| Suppliers | Create supplier | `POST` | `/api/v1/suppliers` |
| Suppliers | Get suppliers by user | `GET` | `/api/v1/suppliers/user/{userId}` |
| Suppliers | Get suppliers by status | `GET` | `/api/v1/suppliers/status/{status}` |
| Suppliers | Get suppliers by speciality | `GET` | `/api/v1/suppliers/speciality/{speciality}` |
| Roast Profiles | Get roast profile by id | `GET` | `/api/v1/roast-profiles/{roastProfileId}` |
| Roast Profiles | Update roast profile | `PUT` | `/api/v1/roast-profiles/{roastProfileId}` |
| Roast Profiles | Delete roast profile | `DELETE` | `/api/v1/roast-profiles/{roastProfileId}` |
| Roast Profiles | Get all roast profiles | `GET` | `/api/v1/roast-profiles` |
| Roast Profiles | Create roast profile | `POST` | `/api/v1/roast-profiles` |
| Roast Profiles | Get roast profiles by user id | `GET` | `/api/v1/roast-profiles/user/{userId}` |
| Roast Profiles | Get favorite roast profiles | `GET` | `/api/v1/roast-profiles/favorites` |
| Roast Profiles | Get roast profiles by coffee lot id | `GET` | `/api/v1/roast-profiles/coffee-lot/{coffeeLotId}` |

# Coffee Lots Endpoints

## Coffee Lots - Get coffee lot by id
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/coffee-lots/{coffeeLotId}</code> </p>

### Parametros
| Campo         | Tipo              | Descripcion |
| ------------- | ----------------- | ----------- |
| `coffeeLotId` | `integer($int64)` | Requerido.  |

### Request Example

```GET /api/v1/coffee-lots/{coffeeLotId}```

```json
{
  "coffeeLotId": 11,
  "supplierId": 1,
  "userId": 1,
  "lotName": "Miku Coffee",
  "coffeeType": "Arábica",
  "origin": "Corea",
  "altitudeMeters": 10,
  "status": "green",
  "remainingWeight": 1,
  "processingMethod": "Lavado",
  "certifications": []
}
```

### Success Response

**HTTP 200**
```json
{
  "coffeeLotId": 11,
  "supplierId": 1,
  "userId": 1,
  "lotName": "Miku Coffee",
  "coffeeType": "Arábica",
  "origin": "Corea",
  "altitudeMeters": 10,
  "status": "green",
  "remainingWeight": 1,
  "processingMethod": "Lavado",
  "certifications": []
}
```

### Default Response

| Campo              | Tipo            | Descripcion                                               |
| ------------------ | --------------- | --------------------------------------------------------- |
| `coffeeLotId`      | `number`        | Identificador del lote de café.                           |
| `supplierId`       | `number`        | Identificador del proveedor asociado al lote.             |
| `userId`           | `number`        | Identificador del usuario propietario o asociado al lote. |
| `lotName`          | `string`        | Nombre del lote de café.                                  |
| `coffeeType`       | `string`        | Tipo de café registrado para el lote.                     |
| `origin`           | `string`        | Origen del café.                                          |
| `altitudeMeters`   | `number`        | Altitud en metros asociada al cultivo del café.           |
| `status`           | `string`        | Estado actual del lote de café.                           |
| `remainingWeight`  | `number`        | Peso restante disponible del lote.                        |
| `processingMethod` | `string`        | Método de procesamiento utilizado para el café.           |
| `certifications`   | `array[string]` |                                                           |


### Extra Response

No Aplica

### Error 4xx / 5xx

```{"message": "Coffee lot not found"}```

## Coffee Lots - Update coffee lot 
<p> <span style="background:#FD7E14;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">PUT</span> <code>/api/v1/coffee-lots/{coffeeLotId}</code> </p>


### Parametros
| Campo         | Tipo              | Descripcion |
| ------------- | ----------------- | ----------- |
| `coffeeLotId` | `integer($int64)` | Requerido.  |

### Request Example

```GET /api/v1/coffee-lots/{coffeeLotId}```


```json
{
  "lotName": "Mik Coffee",
  "supplierId": 1,
  "userId": 1,
  "coffeeType": "Arábica",
  "origin": "Japon",
  "altitudeMeters": 110,
  "processingMethod": "Lavado",
  "certifications": []
}
```
### Success Response

**HTTP 200**

```json
{
  "coffeeLotId": 11,
  "supplierId": 1,
  "userId": 1,
  "lotName": "Mik Coffee",
  "coffeeType": "Arábica",
  "origin": "Japon",
  "altitudeMeters": 110,
  "status": "green",
  "remainingWeight": 1,
  "processingMethod": "Lavado",
  "certifications": []
}
```
### Default Response

| Campo              | Tipo            | Descripcion                                               |
| ------------------ | --------------- | --------------------------------------------------------- |
| `coffeeLotId`      | `number`        | Identificador del lote de café.                           |
| `supplierId`       | `number`        | Identificador del proveedor asociado al lote.             |
| `userId`           | `number`        | Identificador del usuario propietario o asociado al lote. |
| `lotName`          | `string`        | Nombre del lote de café.                                  |
| `coffeeType`       | `string`        | Tipo de café registrado para el lote.                     |
| `origin`           | `string`        | Origen del café.                                          |
| `altitudeMeters`   | `number`        | Altitud en metros asociada al cultivo del café.           |
| `status`           | `string`        | Estado actual del lote de café.                           |
| `remainingWeight`  | `number`        | Peso restante disponible del lote.                        |
| `processingMethod` | `string`        | Método de procesamiento utilizado para el café.           |
| `certifications`   | `array[string]` | Lista de certificaciones asociadas al lote de café.       |


### Extra Response

No aplica.

### Error 4xx / 5xx

```{"message": "Coffee lot not found"}```

## Coffee Lots - Delete coffee lot
<p> <span style="background:#DC3545;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">DELETE</span> <code>/api/v1/coffee-lots/{coffeeLotId}</code> </p>

### Parametros
| Campo         | Tipo              | Descripcion |
| ------------- | ----------------- | ----------- |
| `coffeeLotId` | `integer($int64)` | Requerido.  |

### Request Example

```DELETE /api/v1/coffee-lots/{coffeeLotId}```

### Success Response
```json
{
  "message": "Coffee lot deleted"
}
```
### Default Response
No aplica.

### Extra Response
No aplica.

### Error 4xx / 5xx

```{"message": "Coffee lot not found"}```


## Coffee Lots - Get all coffee lots
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/coffee-lots</code> </p>

### Parametros
Sin parametros.
### Request Example
GET /api/v1/coffee-lots
### Success Response

**HTTP 200**

```json
[
  {
    "coffeeLotId": 12,
    "supplierId": 2,
    "userId": 8,
    "lotName": "LOTE 1 user 8",
    "coffeeType": "Arábica",
    "origin": "El Salvador",
    "altitudeMeters": 1000,
    "status": "green",
    "remainingWeight": 0,
    "processingMethod": "Fermentación anaeróbica",
    "certifications": [
      "FAIR_TRADE"
    ]
  },
  {
    "coffeeLotId": 13,
    "supplierId": 2,
    "userId": 8,
    "lotName": "Lote 2 user 8",
    "coffeeType": "Arábica",
    "origin": "Montaña Io",
    "altitudeMeters": 200,
    "status": "green",
    "remainingWeight": 30,
    "processingMethod": "Honey",
    "certifications": [
      "ORGANIC"
    ]
  }
]
```

### Default Response
| Campo | Tipo    | Descripcion                                  |
| ----- | ------- | -------------------------------------------- |
| `[]`  | `array` | Arreglo de objetos `CoffeeLot`. |

### Extra Response

| Campo              | Tipo            | Descripcion                                               |
| ------------------ | --------------- | --------------------------------------------------------- |
| `coffeeLotId`      | `number`        | Identificador del lote de café.                           |
| `supplierId`       | `number`        | Identificador del proveedor asociado al lote.             |
| `userId`           | `number`        | Identificador del usuario propietario o asociado al lote. |
| `lotName`          | `string`        | Nombre del lote de café.                                  |
| `coffeeType`       | `string`        | Tipo de café registrado para el lote.                     |
| `origin`           | `string`        | Origen del café.                                          |
| `altitudeMeters`   | `number`        | Altitud en metros asociada al cultivo del café.           |
| `status`           | `string`        | Estado actual del lote de café.                           |
| `remainingWeight`  | `number`        | Peso restante disponible del lote.                        |
| `processingMethod` | `string`        | Método de procesamiento utilizado para el café.           |
| `certifications`   | `array[string]` | Lista de certificaciones asociadas al lote de café.       |

### Error 4xx / 5xx

No Aplica

## Coffee Lots - Create coffee lot
<p> <span style="background:#198754;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">POST</span> <code>/api/v1/coffee-lots</code> </p>

### Parametros
| Campo              | Tipo            | Descripcion                                               |
| ------------------ | --------------- | --------------------------------------------------------- |
| `supplierId`       | `number`        | Identificador del proveedor asociado al lote.             |
| `userId`           | `number`        | Identificador del usuario propietario o asociado al lote. |
| `lotName`          | `string`        | Nombre del lote de café.                                  |
| `coffeeType`       | `string`        | Tipo de café registrado para el lote.                     |
| `origin`           | `string`        | Origen del café.                                          |
| `altitudeMeters`   | `number`        | Altitud en metros asociada al cultivo del café.           |
| `status`           | `string`        | Estado actual del lote de café.                           |
| `remainingWeight`  | `number`        | Peso restante disponible del lote.                        |
| `processingMethod` | `string`        | Método de procesamiento utilizado para el café.           |
| `certifications`   | `array[string]` | Lista de certificaciones asociadas al lote de café.       |

### Request Example

```json
{
  "supplierId": 0,
  "userId": 0,
  "lotName": "string",
  "coffeeType": "string",
  "origin": "string",
  "altitudeMeters": 0,
  "status": "string",
  "initialWeight": 0,
  "processingMethod": "string",
  "certifications": [
    "string"
  ]
}
```
### Success Response

**HTTP 200**

 {
    "coffeeLotId": 13,
    "supplierId": 2,
    "userId": 8,
    "lotName": "Lote 2 user 8",
    "coffeeType": "Arábica",
    "origin": "Montaña Io",
    "altitudeMeters": 200,
    "status": "green",
    "remainingWeight": 30,
    "processingMethod": "Honey",
    "certifications": [
      "ORGANIC"
    ]
  }

### Default Response

| Campo              | Tipo            | Descripcion                                               |
| ------------------ | --------------- | --------------------------------------------------------- |
| `coffeeLotId`      | `number`        | Identificador del lote de café.                           |
| `supplierId`       | `number`        | Identificador del proveedor asociado al lote.             |
| `userId`           | `number`        | Identificador del usuario propietario o asociado al lote. |
| `lotName`          | `string`        | Nombre del lote de café.                                  |
| `coffeeType`       | `string`        | Tipo de café registrado para el lote.                     |
| `origin`           | `string`        | Origen del café.                                          |
| `altitudeMeters`   | `number`        | Altitud en metros asociada al cultivo del café.           |
| `status`           | `string`        | Estado actual del lote de café.                           |
| `remainingWeight`  | `number`        | Peso restante disponible del lote.                        |
| `processingMethod` | `string`        | Método de procesamiento utilizado para el café.           |
| `certifications`   | `array[string]` | Lista de certificaciones asociadas al lote de café.       |


### Extra Response

No aplica.

### Error 4xx / 5xx

#### Bad Request
```json
{
  "message": "{Field} is required and must be positive"
}

{
  "message": "Certificación inválida: 'string'. Valores permitidos: ORGANIC, FAIR_TRADE, RAINFOREST"
}

{
  "message": "El tipo de café debe ser uno de: [Arábica, Robusta, Mezcla]"
}

{
  "message": "El status debe ser uno de: [green, roasted]"
}

{
  "message": "El procesamiento debe ser uno de: [Lavado, Natural, Honey, Fermentación anaeróbica, Maceración carbónica]"
}

```

#### Otros

No Aplica

## Coffee Lots - Update coffee lot status
<p> <span style="background:#FD7E14;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">PATCH</span> <code>/api/v1/coffee-lots/{coffeeLotId}/status</code> </p>

### Parametros
| Campo         | Tipo              | Descripcion |
| ------------- | ----------------- | ----------- |
| `coffeeLotId` | `integer($int64)` | Requerido.  |

### Request Example

```DELETE /api/v1/coffee-lots/{coffeeLotId}```

### Request Example
```json 
{
  "status": "string"
}
```
### Success Response

**HTTP 200**

```json
{
  "supplierId": 0,
  "userId": 0,
  "lotName": "string",
  "coffeeType": "string",
  "origin": "string",
  "altitudeMeters": 0,
  "status": "string",
  "initialWeight": 0,
  "processingMethod": "string",
  "certifications": [
    "string"
  ]
}
```
### Default Response

| Campo              | Tipo            | Descripcion                                               |
| ------------------ | --------------- | --------------------------------------------------------- |
| `coffeeLotId`      | `number`        | Identificador del lote de café.                           |
| `supplierId`       | `number`        | Identificador del proveedor asociado al lote.             |
| `userId`           | `number`        | Identificador del usuario propietario o asociado al lote. |
| `lotName`          | `string`        | Nombre del lote de café.                                  |
| `coffeeType`       | `string`        | Tipo de café registrado para el lote.                     |
| `origin`           | `string`        | Origen del café.                                          |
| `altitudeMeters`   | `number`        | Altitud en metros asociada al cultivo del café.           |
| `status`           | `string`        | Estado actual del lote de café.                           |
| `remainingWeight`  | `number`        | Peso restante disponible del lote.                        |
| `processingMethod` | `string`        | Método de procesamiento utilizado para el café.           |
| `certifications`   | `array[string]` | Lista de certificaciones asociadas al lote de café.       |

### Extra Response

No aplica.

### Error 4xx / 5xx

```json
{
  "message": "El status debe ser uno de: [green, roasted]"
}
```

## Coffee Lots - Consume coffee lot stock
<p> <span style="background:#FD7E14;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">PATCH</span> <code>/api/v1/coffee-lots/{coffeeLotId}/consume-stock</code> </p>

### Parametros
| Campo         | Tipo              | Descripcion |
| ------------- | ----------------- | ----------- |
| `coffeeLotId` | `integer($int64)` | Requerido.  |

### Request Example
```json
{
  "weight": 0,
  "finalProduct": "string",
  "dateUsed": "string"
}
```

### Success Response

**HTTP 200**

```json
{
  "supplierId": 0,
  "userId": 0,
  "lotName": "string",
  "coffeeType": "string",
  "origin": "string",
  "altitudeMeters": 0,
  "status": "string",
  "initialWeight": 0,
  "processingMethod": "string",
  "certifications": [
    "string"
  ]
}
```
### Default Response

| Campo              | Tipo            | Descripcion                                               |
| ------------------ | --------------- | --------------------------------------------------------- |
| `coffeeLotId`      | `number`        | Identificador del lote de café.                           |
| `supplierId`       | `number`        | Identificador del proveedor asociado al lote.             |
| `userId`           | `number`        | Identificador del usuario propietario o asociado al lote. |
| `lotName`          | `string`        | Nombre del lote de café.                                  |
| `coffeeType`       | `string`        | Tipo de café registrado para el lote.                     |
| `origin`           | `string`        | Origen del café.                                          |
| `altitudeMeters`   | `number`        | Altitud en metros asociada al cultivo del café.           |
| `status`           | `string`        | Estado actual del lote de café.                           |
| `remainingWeight`  | `number`        | Peso restante disponible del lote.                        |
| `processingMethod` | `string`        | Método de procesamiento utilizado para el café.           |
| `certifications`   | `array[string]` | Lista de certificaciones asociadas al lote de café.       |

### Extra Response

No aplica.

### Error 4xx / 5xx

```json
{
  "message": "La cantidad consumida debe ser menor o igual a la disponible"
}
```

## Coffee Lots - Get coffee lots by user
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/coffee-lots/user/{userId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `userId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/coffee-lots/user/{userId}`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response


| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx
No Aplica


## Coffee Lots - Get coffee lots by supplier
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/coffee-lots/supplier/{supplierId}</code> </p>


### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/coffee-lots/supplier/{supplierId}`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response

#### Extra Coffee Lot Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx

No Aplica

## Coffee Lots - Get coffee lots by status

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/coffee-lots/status/{status}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `status` | `string` | Requerido. |

### Request Example

`GET /api/v1/coffee-lots/status/{status}`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response

#### Extra Coffee Lot Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx
```json
{
  "message": "El status debe ser uno de: [green, roasted]"
}
```


## Coffee Lots - Get coffee lots by processingMethod 
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>
/api/v1/coffee-lots/processing-method/{processingMethod}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `processingMethod` | `string` | Requerido. |

### Request Example

`GET /api/v1/coffee-lots/processing-method/{processingMethod}`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response

#### Extra Coffee Lot Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx
```json
{
  "message": "El procesamiento debe ser uno de: [Lavado, Natural, Honey, Fermentación anaeróbica, Maceración carbónica]"
}
```

## Coffee Lots - Get coffee lots by origin 
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>
/api/v1/coffee-lots/origin/{origin}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `origin` | `string` | Requerido. |

### Request Example

`GET /api/v1/coffee-lots/origin/{origin}`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response

#### Extra Coffee Lot Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx
No Aplica

## Coffee Lots - Get coffee lots by CoffeeType 
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>
/api/v1/coffee-lots/coffee-type/{coffeeType}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeType` | `string` | Requerido. |

### Request Example

`GET /api/v1/coffee-lots/coffee-type/{coffeeType}`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response

#### Extra Coffee Lot Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx
```json

{
  "message": "El procesamiento debe ser uno de: [Lavado, Natural, Honey, Fermentación anaeróbica, Maceración carbónica]"
}
```

## Coffee Lots - Get Depleted Coffee Lots 
<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>
/api/v1/coffee-lots/depleted</code> </p>

### Parametros

No Aplica

### Request Example

`GET /api/v1/coffee-lots/depleted`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response

#### Extra Coffee Lot Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx

No Aplica

## Coffee Lots - Get Available Coffee Lots 

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>
/api/v1/coffee-lots/available</code> </p>

### Parametros

No Aplica

### Request Example

`GET /api/v1/coffee-lots/availabe`

### Success Response

HTTP 200

```json
[  
  {  
    "coffeeLotId": 11,  
    "supplierId": 1,  
    "userId": 1,  
    "lotName": "Miku Coffee",  
    "coffeeType": "Arábica",  
    "origin": "Corea",  
    "altitudeMeters": 10,  
    "status": "green",  
    "remainingWeight": 1,  
    "processingMethod": "Lavado",  
    "certifications": \[\]  
  }  
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `CoffeeLotResource`. |

### Extra Response

#### Extra Coffee Lot Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café. |
| `supplierId` | `number` | Identificador del proveedor asociado al lote. |
| `userId` | `number` | Identificador del usuario propietario o asociado al lote. |
| `lotName` | `string` | Nombre del lote de café. |
| `coffeeType` | `string` | Tipo de café registrado para el lote. |
| `origin` | `string` | Origen del café. |
| `altitudeMeters` | `number` | Altitud en metros asociada al cultivo del café. |
| `status` | `string` | Estado actual del lote de café. |
| `remainingWeight` | `number` | Peso restante disponible del lote. |
| `processingMethod` | `string` | Método de procesamiento utilizado para el café. |
| `certifications` | `array[string]` |  |

### Error 4xx / 5xx

No Aplica

# Inventory Endpoints

## Inventory Entries - Get Inventory Entries by User

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/inventory-entries/user/{userId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `userId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/inventory-entries/user/{userId}`

### Success Response

HTTP 200

```json
[
  {
    "id": 8,
    "userId": 1,
    "coffeeLotId": 11,
    "quantityUsed": 1,
    "dateUsed": "2026-06-02T01:00:45",
    "finalProduct": "mi"
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `InventoryEntryResource`. |

### Extra Response

#### Extra Inventory Entry Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `id` | `number` | Identificador del registro de inventario. |
| `userId` | `number` | Identificador del usuario asociado al consumo. |
| `coffeeLotId` | `number` | Identificador del lote de café consumido. |
| `quantityUsed` | `number` | Cantidad consumida del lote de café. |
| `dateUsed` | `string` | Fecha y hora en que se registró el consumo. |
| `finalProduct` | `string` | Producto final elaborado con el café consumido. |

### Error 4xx / 5xx

No Aplica


# Suppliers Endpoint

## Suppliers - Get Supplier by Id

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/suppliers/{supplierId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/suppliers/{supplierId}`

### Success Response

HTTP 200

```json
{
  "supplierId": 2,
  "userId": 7,
  "name": "Julian Casablanca",
  "email": "daftpunk@gmail.com",
  "phone": "123123123",
  "location": "Peru",
  "status": "DRAFT",
  "specialities": [
    "string"
  ]
}
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `number` | Identificador del proveedor. |
| `userId` | `number` | Identificador del usuario asociado al proveedor. |
| `name` | `string` | Nombre del proveedor. |
| `email` | `string` | Correo electrónico del proveedor. |
| `phone` | `string` | Número de teléfono del proveedor. |
| `location` | `string` | Ubicación del proveedor. |
| `status` | `string` | Estado actual del proveedor. |
| `specialities` | `array[string]` | Lista de especialidades del proveedor. |

### Extra Response

No Aplica

### Error 4xx / 5xx

No Aplica

---

## Suppliers - Update Supplier

<p> <span style="background:#FD7E14;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">PUT</span> <code>/api/v1/suppliers/{supplierId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `integer($int64)` | Requerido. |

### Request Example

```json
{
  "name": "string",
  "email": "string",
  "phone": "string",
  "location": "string",
  "status": "string",
  "specialities": [
    "string"
  ]
}
```

### Success Response

HTTP 200

```json
{}
```

### Default Request Body

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `name` | `string` | Nombre del proveedor. |
| `email` | `string` | Correo electrónico del proveedor. |
| `phone` | `string` | Número de teléfono del proveedor. |
| `location` | `string` | Ubicación del proveedor. |
| `status` | `string` | Estado del proveedor. |
| `specialities` | `array[string]` | Lista de especialidades del proveedor. |

### Extra Response

No Aplica

### Error 4xx / 5xx

No Aplica

---

## Suppliers - Delete Supplier

<p> <span style="background:#DC3545;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">DELETE</span> <code>/api/v1/suppliers/{supplierId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `integer($int64)` | Requerido. |

### Request Example

`DELETE /api/v1/suppliers/{supplierId}`

### Success Response

HTTP 200

```json
{
  "message": "Supplier deleted"
}
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `message` | `string` | Mensaje de confirmación de eliminación. |

### Extra Response

No Aplica

### Error 4xx / 5xx

```json
{
  "message": "Supplier no existe"
}
```

---

## Suppliers - Get All Suppliers

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/suppliers</code> </p>

### Parametros

No Aplica

### Request Example

`GET /api/v1/suppliers`

### Success Response

HTTP 200

```json
[
  {
    "supplierId": 2,
    "userId": 7,
    "name": "Julian Casablanca",
    "email": "daftpunk@gmail.com",
    "phone": "123123123",
    "location": "Peru",
    "status": "DRAFT",
    "specialities": [
      "dasda"
    ]
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `SupplierResource`. |

### Extra Response

#### Extra Supplier Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `number` | Identificador del proveedor. |
| `userId` | `number` | Identificador del usuario asociado al proveedor. |
| `name` | `string` | Nombre del proveedor. |
| `email` | `string` | Correo electrónico del proveedor. |
| `phone` | `string` | Número de teléfono del proveedor. |
| `location` | `string` | Ubicación del proveedor. |
| `status` | `string` | Estado actual del proveedor. |
| `specialities` | `array[string]` | Lista de especialidades del proveedor. |

### Error 4xx / 5xx

No Aplica

---

## Suppliers - Create Supplier

<p> <span style="background:#198754;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">POST</span> <code>/api/v1/suppliers</code> </p>

### Parametros

No Aplica

### Request Example

```json
{
  "userId": 0,
  "name": "string",
  "email": "string",
  "phone": "string",
  "location": "string",
  "status": "string",
  "specialities": [
    "string"
  ]
}
```

### Success Response

HTTP 200

```json
  {
    "supplierId": 2,
    "userId": 7,
    "name": "Julian Casablanca",
    "email": "daftpunk@gmail.com",
    "phone": "123123123",
    "location": "Peru",
    "status": "DRAFT",
    "specialities": [
      "string"
    ]
  }
```

### Default Request Body

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `userId` | `number` | Identificador del usuario asociado al proveedor. |
| `name` | `string` | Nombre del proveedor. |
| `email` | `string` | Correo electrónico del proveedor. |
| `phone` | `string` | Número de teléfono del proveedor. |
| `location` | `string` | Ubicación del proveedor. |
| `status` | `string` | Estado del proveedor. |
| `specialities` | `array[string]` | Lista de especialidades del proveedor. |

### Extra Response

No Aplica

### Error 4xx / 5xx

No Aplica

## Suppliers - Get Suppliers by User

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/suppliers/user/{userId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `userId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/suppliers/user/{userId}`

### Success Response

HTTP 200

```json
[
  {
    "supplierId": 2,
    "userId": 7,
    "name": "Julian Casablanca",
    "email": "daftpunk@gmail.com",
    "phone": "123123123",
    "location": "Peru",
    "status": "DRAFT",
    "specialities": [
      "dasda"
    ]
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `SupplierResource`. |

### Extra Response

#### Extra Supplier Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `number` | Identificador del proveedor. |
| `userId` | `number` | Identificador del usuario asociado al proveedor. |
| `name` | `string` | Nombre del proveedor. |
| `email` | `string` | Correo electrónico del proveedor. |
| `phone` | `string` | Número de teléfono del proveedor. |
| `location` | `string` | Ubicación del proveedor. |
| `status` | `string` | Estado actual del proveedor. |
| `specialities` | `array[string]` | Lista de especialidades del proveedor. |

### Error 4xx / 5xx

No Aplica

---

## Suppliers - Get Suppliers by Status

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/suppliers/status/{status}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `status` | `string` | Requerido. |

### Request Example

`GET /api/v1/suppliers/status/{status}`

### Success Response

HTTP 200

```json
[
  {
    "supplierId": 2,
    "userId": 7,
    "name": "Julian Casablanca",
    "email": "daftpunk@gmail.com",
    "phone": "123123123",
    "location": "Peru",
    "status": "DRAFT",
    "specialities": [
      "dasda"
    ]
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `SupplierResource`. |

### Extra Response

#### Extra Supplier Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `number` | Identificador del proveedor. |
| `userId` | `number` | Identificador del usuario asociado al proveedor. |
| `name` | `string` | Nombre del proveedor. |
| `email` | `string` | Correo electrónico del proveedor. |
| `phone` | `string` | Número de teléfono del proveedor. |
| `location` | `string` | Ubicación del proveedor. |
| `status` | `string` | Estado actual del proveedor. |
| `specialities` | `array[string]` | Lista de especialidades del proveedor. |

### Error 4xx / 5xx

No Aplica

---

## Suppliers - Get Suppliers by Speciality

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/suppliers/speciality/{speciality}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `speciality` | `string` | Requerido. |

### Request Example

`GET /api/v1/suppliers/speciality/{speciality}`

### Success Response

HTTP 200

```json
[
  {
    "supplierId": 2,
    "userId": 7,
    "name": "Julian Casablanca",
    "email": "daftpunk@gmail.com",
    "phone": "123123123",
    "location": "Peru",
    "status": "DRAFT",
    "specialities": [
      "dasda"
    ]
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `SupplierResource`. |

### Extra Response

#### Extra Supplier Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `supplierId` | `number` | Identificador del proveedor. |
| `userId` | `number` | Identificador del usuario asociado al proveedor. |
| `name` | `string` | Nombre del proveedor. |
| `email` | `string` | Correo electrónico del proveedor. |
| `phone` | `string` | Número de teléfono del proveedor. |
| `location` | `string` | Ubicación del proveedor. |
| `status` | `string` | Estado actual del proveedor. |
| `specialities` | `array[string]` | Lista de especialidades del proveedor. |

### Error 4xx / 5xx

No Aplica

# Roast Profiles

## Roast Profiles - Get Roast Profile by Id

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/roast-profiles/{roastProfileId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/roast-profiles/{roastProfileId}`

### Success Response

HTTP 200

```json
{
  "roastProfileId": 9,
  "coffeeLotId": 11,
  "userId": 1,
  "name": "Miku Coffee",
  "temperatureStart": 10,
  "temperatureEnd": 20,
  "durationSeconds": 10,
  "type": "MEDIUM",
  "isFavorite": true,
  "acidity": 5,
  "sweetness": 5,
  "body": 5
}
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `number` | Identificador del perfil de tostado. |
| `coffeeLotId` | `number` | Identificador del lote de café asociado. |
| `userId` | `number` | Identificador del usuario asociado al perfil. |
| `name` | `string` | Nombre del perfil de tostado. |
| `temperatureStart` | `number` | Temperatura inicial del proceso de tostado. |
| `temperatureEnd` | `number` | Temperatura final del proceso de tostado. |
| `durationSeconds` | `number` | Duración del tostado en segundos. |
| `type` | `string` | Tipo de tostado. |
| `isFavorite` | `boolean` | Indica si el perfil está marcado como favorito. |
| `acidity` | `number` | Nivel de acidez. |
| `sweetness` | `number` | Nivel de dulzor. |
| `body` | `number` | Nivel de cuerpo. |

### Extra Response

No Aplica

### Error 4xx / 5xx

```json
{
  "message": "Roast profile not found"
}
```

---

## Roast Profiles - Update Roast Profile

<p> <span style="background:#FD7E14;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">PUT</span> <code>/api/v1/roast-profiles/{roastProfileId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `integer($int64)` | Requerido. |

### Request Example

```json
{
  "name": "string",
  "temperatureStart": 0,
  "temperatureEnd": 0,
  "durationSeconds": 0,
  "type": "string",
  "isFavorite": true,
  "acidity": 0,
  "sweetness": 0,
  "body": 0
}
```

### Success Response

HTTP 200

```json
{
  "roastProfileId": 9,
  "coffeeLotId": 11,
  "userId": 1,
  "name": "Miku Coffee",
  "temperatureStart": 10,
  "temperatureEnd": 20,
  "durationSeconds": 10,
  "type": "MEDIUM",
  "isFavorite": true,
  "acidity": 5,
  "sweetness": 5,
  "body": 5
}
```

### Default Request Body

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `name` | `string` | Nombre del perfil de tostado. |
| `temperatureStart` | `number` | Temperatura inicial del proceso de tostado. |
| `temperatureEnd` | `number` | Temperatura final del proceso de tostado. |
| `durationSeconds` | `number` | Duración del tostado en segundos. |
| `type` | `string` | Tipo de tostado. |
| `isFavorite` | `boolean` | Indica si el perfil está marcado como favorito. |
| `acidity` | `number` | Nivel de acidez. |
| `sweetness` | `number` | Nivel de dulzor. |
| `body` | `number` | Nivel de cuerpo. |

### Extra Response

No Aplica

### Error 4xx / 5xx

```json
{
  "message": "Roast profile not found"
}
```

---

## Roast Profiles - Delete Roast Profile

<p> <span style="background:#DC3545;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">DELETE</span> <code>/api/v1/roast-profiles/{roastProfileId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `integer($int64)` | Requerido. |

### Request Example

`DELETE /api/v1/roast-profiles/{roastProfileId}`

### Success Response

HTTP 200

```json
{
  "message": "Roast profile deleted"
}
```

### Default Response

Información no disponible en la documentación proporcionada.

### Extra Response

No Aplica

### Error 4xx / 5xx

No Aplica

---

## Roast Profiles - Get All Roast Profiles

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/roast-profiles</code> </p>

### Parametros

No Aplica

### Request Example

`GET /api/v1/roast-profiles`

### Success Response

HTTP 200

```json
[
  {
    "roastProfileId": 9,
    "coffeeLotId": 11,
    "userId": 1,
    "name": "Miku Coffee",
    "temperatureStart": 10,
    "temperatureEnd": 20,
    "durationSeconds": 10,
    "type": "MEDIUM",
    "isFavorite": true,
    "acidity": 5,
    "sweetness": 5,
    "body": 5
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `RoastProfileResource`. |

### Extra Response

#### Extra Roast Profile Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `number` | Identificador del perfil de tostado. |
| `coffeeLotId` | `number` | Identificador del lote de café asociado. |
| `userId` | `number` | Identificador del usuario asociado al perfil. |
| `name` | `string` | Nombre del perfil de tostado. |
| `temperatureStart` | `number` | Temperatura inicial del proceso de tostado. |
| `temperatureEnd` | `number` | Temperatura final del proceso de tostado. |
| `durationSeconds` | `number` | Duración del tostado en segundos. |
| `type` | `string` | Tipo de tostado. |
| `isFavorite` | `boolean` | Indica si el perfil está marcado como favorito. |
| `acidity` | `number` | Nivel de acidez. |
| `sweetness` | `number` | Nivel de dulzor. |
| `body` | `number` | Nivel de cuerpo. |

### Error 4xx / 5xx

No Aplica

---

## Roast Profiles - Create Roast Profile

<p> <span style="background:#198754;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">POST</span> <code>/api/v1/roast-profiles</code> </p>

### Parametros

No Aplica

### Request Example

```json
{
  "coffeeLotId": 0,
  "userId": 0,
  "name": "string",
  "temperatureStart": 0,
  "temperatureEnd": 0,
  "durationSeconds": 0,
  "type": "string",
  "isFavorite": true,
  "acidity": 0,
  "sweetness": 0,
  "body": 0
}
```

### Success Response

HTTP 200

```json
{
  "roastProfileId": 9,
  "coffeeLotId": 11,
  "userId": 1,
  "name": "Miku Coffee",
  "temperatureStart": 10,
  "temperatureEnd": 20,
  "durationSeconds": 10,
  "type": "MEDIUM",
  "isFavorite": true,
  "acidity": 5,
  "sweetness": 5,
  "body": 5
}
```

### Default Request Body

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `number` | Identificador del lote de café asociado. |
| `userId` | `number` | Identificador del usuario asociado al perfil. |
| `name` | `string` | Nombre del perfil de tostado. |
| `temperatureStart` | `number` | Temperatura inicial del proceso de tostado. |
| `temperatureEnd` | `number` | Temperatura final del proceso de tostado. |
| `durationSeconds` | `number` | Duración del tostado en segundos. |
| `type` | `string` | Tipo de tostado. |
| `isFavorite` | `boolean` | Indica si el perfil está marcado como favorito. |
| `acidity` | `number` | Nivel de acidez. |
| `sweetness` | `number` | Nivel de dulzor. |
| `body` | `number` | Nivel de cuerpo. |

### Extra Response

No Aplica

### Error 4xx / 5xx

No Aplica

## Roast Profiles - Get Roast Profiles by User Id

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/roast-profiles/user/{userId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `userId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/roast-profiles/user/{userId}`

### Success Response

HTTP 200

```json
[
  {
    "roastProfileId": 9,
    "coffeeLotId": 11,
    "userId": 1,
    "name": "Miku Coffee",
    "temperatureStart": 10,
    "temperatureEnd": 20,
    "durationSeconds": 10,
    "type": "MEDIUM",
    "isFavorite": true,
    "acidity": 5,
    "sweetness": 5,
    "body": 5
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `RoastProfileResource`. |

### Extra Response

#### Extra Roast Profile Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `number` | Identificador del perfil de tostado. |
| `coffeeLotId` | `number` | Identificador del lote de café asociado. |
| `userId` | `number` | Identificador del usuario asociado al perfil. |
| `name` | `string` | Nombre del perfil de tostado. |
| `temperatureStart` | `number` | Temperatura inicial del proceso de tostado. |
| `temperatureEnd` | `number` | Temperatura final del proceso de tostado. |
| `durationSeconds` | `number` | Duración del tostado en segundos. |
| `type` | `string` | Tipo de tostado. |
| `isFavorite` | `boolean` | Indica si el perfil está marcado como favorito. |
| `acidity` | `number` | Nivel de acidez. |
| `sweetness` | `number` | Nivel de dulzor. |
| `body` | `number` | Nivel de cuerpo. |

### Error 4xx / 5xx

No Aplica

---

## Roast Profiles - Get Favorite Roast Profiles

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/roast-profiles/favorites</code> </p>

### Parametros

No Aplica

### Request Example

`GET /api/v1/roast-profiles/favorites`

### Success Response

HTTP 200

```json
[
  {
    "roastProfileId": 9,
    "coffeeLotId": 11,
    "userId": 1,
    "name": "Miku Coffee",
    "temperatureStart": 10,
    "temperatureEnd": 20,
    "durationSeconds": 10,
    "type": "MEDIUM",
    "isFavorite": true,
    "acidity": 5,
    "sweetness": 5,
    "body": 5
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `RoastProfileResource`. |

### Extra Response

#### Extra Roast Profile Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `number` | Identificador del perfil de tostado. |
| `coffeeLotId` | `number` | Identificador del lote de café asociado. |
| `userId` | `number` | Identificador del usuario asociado al perfil. |
| `name` | `string` | Nombre del perfil de tostado. |
| `temperatureStart` | `number` | Temperatura inicial del proceso de tostado. |
| `temperatureEnd` | `number` | Temperatura final del proceso de tostado. |
| `durationSeconds` | `number` | Duración del tostado en segundos. |
| `type` | `string` | Tipo de tostado. |
| `isFavorite` | `boolean` | Indica si el perfil está marcado como favorito. |
| `acidity` | `number` | Nivel de acidez. |
| `sweetness` | `number` | Nivel de dulzor. |
| `body` | `number` | Nivel de cuerpo. |

### Error 4xx / 5xx

No Aplica

---

## Roast Profiles - Get Roast Profiles by Coffee Lot Id

<p> <span style="background:#0A58CA;color:#ffffff;padding:4px 10px;border-radius:999px;font-weight:700;">GET</span> <code>/api/v1/roast-profiles/coffee-lot/{coffeeLotId}</code> </p>

### Parametros

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `coffeeLotId` | `integer($int64)` | Requerido. |

### Request Example

`GET /api/v1/roast-profiles/coffee-lot/{coffeeLotId}`

### Success Response

HTTP 200

```json
[
  {
    "roastProfileId": 9,
    "coffeeLotId": 11,
    "userId": 1,
    "name": "Miku Coffee",
    "temperatureStart": 10,
    "temperatureEnd": 20,
    "durationSeconds": 10,
    "type": "MEDIUM",
    "isFavorite": true,
    "acidity": 5,
    "sweetness": 5,
    "body": 5
  }
]
```

### Default Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `[]` | `array` | Arreglo de objetos `RoastProfileResource`. |

### Extra Response

#### Extra Roast Profile Item Response

| Campo | Tipo | Descripcion |
| ----- | ----- | ----- |
| `roastProfileId` | `number` | Identificador del perfil de tostado. |
| `coffeeLotId` | `number` | Identificador del lote de café asociado. |
| `userId` | `number` | Identificador del usuario asociado al perfil. |
| `name` | `string` | Nombre del perfil de tostado. |
| `temperatureStart` | `number` | Temperatura inicial del proceso de tostado. |
| `temperatureEnd` | `number` | Temperatura final del proceso de tostado. |
| `durationSeconds` | `number` | Duración del tostado en segundos. |
| `type` | `string` | Tipo de tostado. |
| `isFavorite` | `boolean` | Indica si el perfil está marcado como favorito. |
| `acidity` | `number` | Nivel de acidez. |
| `sweetness` | `number` | Nivel de dulzor. |
| `body` | `number` | Nivel de cuerpo. |

### Error 4xx / 5xx

No Aplica
