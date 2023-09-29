# GraphQL with Spring Boot

## Chap 0: Home/Main 
GraphQL is a server-side technology, developed by Facebook to overcome some challenges of RESTful API calls. This series will introduce you to the core concepts of GraphQL and help you set up a server-side application using Java and Spring Boot.

As part of this series, we will try to develop the server side APIs for an online book library.

### Is this for me?
This series is created for Java developers who have some exposure to building Client-Server applications with Spring Boot. Knowledge of Spring Boot is not necessary, but is recommended to understand the workings as we rely a lot on these concepts.
After this series, you will be able to build GraphQL APIs for mobile or web applications.


## Chap 1: GraphQL - Know abouts?
### What is GraphQL?
GraphQL is a new API standard developed by Facebook. It is considered as more flexible and powerful alternative to REST.
It is an execution engine and a data query language.

### Why GraphQL & How it compares with REST
REST advocates the usage of stateless servers and follows well-structured resource-oriented approach to the data. However, when the data gets complex, it gets inefficient also. Sometimes, it is not possible to fetch all the data with a single request and need to invoke multiple API calls required for one single UI screen. Along with this, REST does not provide flexibility to client side application on what they need and server ends up sending a lot of data which might not be needed by client.
This is where GraphQL comes handy. GraphQL structures data in the form of a graph with its powerful query syntax for traversing, retrieving, and modifying data.

#### Can you explain?
To understand this better, let us consider an online book library application that we will develop along this series.

Library Application:
The application consists of users, looking for various books in the application. Users can buy/rent the books they like. They can also mark a book as favorite to refer them later.

For the homepage (or dashboard), we might want to show:
- User Name
- Favorite Books
- My Books

##### UseCase 1: Page header (User Profile)
REST API:
`/users/<id>`

Returning:
```json
{
  "id": "oenksvier23r",
  "name": "John Dow",
  "birthday": "Jan 01 1990",
  "address": {...},
  "pic": "http://profile.example.com/oenksvier23r/image.jpg"
}
```
Here, we need to display only name and profile picture, but server sends other data for the user which is not useful in this case.

##### UseCase 2: View favorite books
REST API:
`/users/<id>/books?favorite=true`

Returning:
```json
{
  "books": [
    {
      "id": "aehirldr23lze3d",
      "title": "Head First Java",
      "authors": "Kathy Sierra and Bert Bates",
      "liked": true
    },
    {
      "id": "afsakefasew343s",
      "title": "Core Java Volume 1 : The Basics",
      "authors": "Cay S. Horstmann and Gary Cornell",
      "liked": true
    },
    {...}
  ]
}
```
Again, we need only titles, but server is sending all the data for the book, not necessary for this place.

Moreover, client needs to invoke 3 API calls to get all the data required to render this UI.

So, we have 2 main problems:
- We have to invoke multiple API calls to render one screen (called **under fetching**)
- We are getting more than what is required (called **over fetching**)

#### But I can optimize and adapt REST APIs
Yes, you can adapt your REST APIs to the client use cases (say screen) so that it returns only the data for display. But this will result in highly coupled server APIs to the UI views. You will end up having multiple endpoints (or data views) for various use cases. And whenever your view evolves, your API will need to be updated accordingly, which is not very scalable.

#### Got it. But, how does GraphQL solve this?
Now that we have seen the challenges with REST, let us jump to see what GraphQL offers us.
First, let us understand how GraphQL works.

GraphQL uses a single endpoint usually `/api/graphql`. However, you can send a request as POST to this endpoint, and describe what data do we exactly need from the server in the request body.

##### Ask what you need
Let us consider the use case above where we want to fetch only name & profile picture of the user. This can be done by the following request payload.

```
query {
  users {
    name
    pic
  }
}
```
For this, the response will contain the values only for name and pic fields. The query will not fetch values of other attributes of the user object. The sample response of this is as below:
```json
{
    "data": {
       "users": [
          {
             "name": "John Dow",
             "pic": "http://profile.example.com/oenksvier23r/image.jpg"
          },
          {
             "name": "Robert Martin",
             "pic": "http://profile.example.com/orksehesr42p/image.jpg"
          }
       ]
    }
 }
```

##### Query multiple data
GraphQL queries help us easily retrieve associated objects in one call, which typically require multiple API calls in REST. GraphQL fetches all the data needed by the client in a single call.

Now, let us consider the case where we had to fetch different data like user profile details (name & profile picture) and the favorite books.
```
query {
  users {
    name
    pic
    books (favorite: True) {
      title
    }
  }
}
```
The response of this will look something like:

```json
{
  "data": {
    "user": {
      "name": "John Dow",
      "pic": "http://profile.example.com/oenksvier23r/image.jpg"
    },
    "books": [
      {
        "title": "Head First Java",
        "liked": true
      },
      {
        "title": "Core Java Volume 1 : The Basics",
        "liked": true
      }
    ]
  }
}
```




## Chap 2: GraphQL syntax and details
- What is query/ mutation.. output, input etc

## Chap 2B: How to access
- GraphiQL UI
- command line (curl)
- Postman?

## Chap 3: Env Setup
Using Maven (optional gradle)

## Chap 4: Basic query & mutation (direct fields)

## Chap 5: Advanced mutation (objects as input)

## Chap 6: Error handling (duplicate & no object found)


## Chap 7: Authentication

## Chap 8: 


## My References
- https://www.tutorialspoint.com/graphql/graphql_introduction.htm
- https://medium.com/swlh/grapql-from-theory-to-real-world-with-spring-boot-d6b46a28027b
- https://medium.com/swlh/understanding-graphql-error-handling-mechanisms-in-spring-boot-604301c9bedb
- https://medium.com/@philippechampion58/secure-your-graphql-api-within-a-spring-boot-app-72961fbe9232
- https://medium.com/@philippechampion58/testing-your-graphql-apis-in-a-spring-boot-app-9fe02ebccc35
- https://www.maxivanov.io/make-graphql-requests-with-curl/
- 