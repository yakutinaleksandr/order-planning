# order-planning

Test project

Java 11 + Spring + MongoDB

in case of errors during setting up please add 
"-Djdk.tls.client.protocols=TLSv1.2"
to VM options

location of customers and warehousese is represented as point in 2D

distance can be calculated with Pythagoras theorem

Assumption: amount of product doesn't play any role.

ENDPOINTS

- POST /api/customer

  creates new customer

  body sample: 
  {
    "username": "foo",
    "location": {
      "x": 11,
      "y": 22
    }
  }

  returns created customer
  {
    "id": ...
    "username": "foo",
    "location": {
      "x": 11,
      "y": 22
    }
  }
  
- POST /api/order

  place new order(finds nearest warehouse with product and calculates distance between customer and warehouse)

  body sample: 
  {
    "customerId": "foo",
    "productId": "bar"
  }

  returns nearest warehouse and distance to customer
  {
    "warehouseId": "foo"
    "warehouseName": "bar",
    "distance": 12.3
  }
  
Data in DB:

- warehouses collection:

{ _id: ObjectID("6019931b102e241aa073b4fe"),
  name: 'w1',
  location: { x: 10, y: 10 } }
  
{ _id: ObjectID("60199350102e241aa073b4ff"),
  name: 'w2',
  location: { x: -10, y: 10 } }
  
{ _id: ObjectID("6019935a102e241aa073b500"),
  name: 'w3',
  location: { x: 10, y: -10 } }
  
{ _id: ObjectID("60199362102e241aa073b501"),
  name: 'w4',
  location: { x: -10, y: -10 } }
  
- products collection:

{ _id: ObjectID("60199484102e241aa073b502"),
  name: 'Toy',
  warehouseIds: [ '6019931b102e241aa073b4fe', '60199350102e241aa073b4ff' ] }
  
{ _id: ObjectID("6019a8be102e241aa073b503"),
  name: 'Book',
  warehouseIds: [ '60199350102e241aa073b4ff', '6019935a102e241aa073b500' ] }
  
{ _id: ObjectID("6019a922102e241aa073b504"),
  name: 'Pillow',
  warehouseIds: [ '6019935a102e241aa073b500', '60199362102e241aa073b501' ] }
  
{ _id: ObjectID("6019a957102e241aa073b505"),
  name: 'Vase',
  warehouseIds: [ '6019931b102e241aa073b4fe', '60199362102e241aa073b501' ] }
  
- customers collection:

{ _id: ObjectID("60199591d324c922e939ea69"),
  username: 'customer1',
  location: { x: 1, y: 1 },
  _class: 'com.example.orderplanning.model.Customer' }
  
remote DB is used

to connect to it use uri in application.properties

db name "test"
