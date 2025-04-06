---

# **Sales Management API**

RESTful API to manage a sales system, including products, categories, orders, users, and payments. The project follows best API design practices, using Spring Boot and JPA to interact with the database.

---

## **Features**

### Products Management:
- List all products
- Search product by ID
- Create, update, and delete products

### Categories Management:
- List all categories
- Search category by ID
- Create, update, and delete categories

### Users Management:
- List all users
- Search user by ID
- Create, update, and delete users

### Orders Management:
- Register orders linked to users
- Add items to the order, with quantity and price
- Associate payment with orders

### Exception Handling:
- Clear error responses (400, 404) with detailed messages

---

## **Technologies Used**

### Backend:
- Spring Boot  
- Spring Data JPA (Hibernate)  
- Jackson  

### Database:
- **H2 Database**  
- **MySQL**  

### Additional Dependencies:
- **Lombok** (to reduce code boilerplate)

---

## **Project Structure**

### Entities:
- Contains classes that represent tables in the database, like `Product`, `Category`, `Order`, and `User`.

### Repository:
- Interfaces to interact with the database using JPA.

### Services:
- Contains the business logic and validation rules.

### Resources:
- Defines the RESTful endpoints and exposes the operations available for each entity.

### Exceptions:
- Classes for centralized error handling and clear messages to the client.

---
## **Database relationships**

![entities](https://github.com/user-attachments/assets/b0aff957-7eea-431b-9ece-def180648cc7)

---

## **Endpoints**

### **Products**
- `GET /products` - List all products.  
- `GET /products/{id}` - Search for a product by ID.  
- `POST /products` - Create a new product.  
- `PUT /products/{id}` - Update a product by ID.  
- `DELETE /products/{id}` - Delete a product by ID.  

### **Categories**
- `GET /categories` - List all categories.  
- `GET /categories/{id}` - Search for a category by ID.  
- `POST /categories` - Create a new category.  
- `PUT /categories/{id}` - Update a category by ID.  
- `DELETE /categories/{id}` - Delete a category by ID.  

*(Similar endpoints for **Orders** and **Users** can be added.)*

---

## **How to Run the Project**

### **Prerequisites**
- **JDK 21+**  
- **Maven**

### **Steps**
1. Clone this repository:
   ```bash
   git clone https://github.com/Thiaghoul/workshop-springboot3-jpa.git
   ```

2. Configure the database in `application.properties` to use **H2**:
   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.h2.console.enabled=true
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   ```

3. Run the project:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the **H2 Console**:
   - **URL:** `http://localhost:8080/h2-console`  
   - **JDBC URL:** `jdbc:h2:mem:testdb`  
   - **User:** `sa`  
   - **Password:** *(empty)*

5. Test the API:
   - `http://localhost:8080/products`  
   - `http://localhost:8080/categories`  
   - *(and other endpoints)*

---

## **Request Example**

### Create a Category

**Endpoint:**  
`POST /categories`

**Body:**  
```json
{
    "name" : "Food and vegetables"
}
```

**Response:**  
```json
{
    "id": 5,
    "name": "Food and vegetables"
}

```
### Create a Product

**Endpoint:**  
`POST /products`

**Body:**  
```json
{
    "name": "ram memory",
    "description": "a common computing acronym that stands for random-access memory.",
    "price": 90.5,
    "imgUrl": "",
    "categories": [
        {
            "id": 1,
            "name" : "Eletronics"

        }
    ]
}
```

**Response:**  
```json
{
    "id": 9,
    "name": "ram memory",
    "description": "a common computing acronym that stands for random-access memory.",
    "price": 90.5,
    "imgUrl": "",
    "categories": [
        {
            "id": 1,
            "name": "Eletronics"
        }
    ]
}

```

---

## **Author**

Developed by Thiago Henriques Nunes
