# System Architecture Diagrams

Since image generation is currently unavailable, you can use these exact Mermaid codes in any markdown viewer (like GitHub) or paste them into [Mermaid Live Editor](https://mermaid.live) to get perfect, downloadable `.png` diagrams for your capstone project.

## 1. Class Diagram (`class-diagram.png` equivalent)

```mermaid
classDiagram
    class MainServer {
        +main(String[] args)
    }
    
    class SystemFacade {
        -Store store
        +SystemFacade()
        +getStore() Store
    }

    class Store {
        -List~Item~ items
        -List~OrderCart~ orders
        +getAllItems() List~Item~
        +makeOrder(String name, String email, List~String~ itemIds) OrderCart
    }

    class Item {
        <<abstract>>
        -String id
        -String title
        -String desc
        -double basePrice
        -String img
        +calculateFinalPrice() double
    }

    class Meal {
        -boolean isSpicy
        +calculateFinalPrice() double
    }

    class BeverageItem {
        -boolean isCold
        +calculateFinalPrice() double
    }

    class Person {
        -String name
        -String email
    }

    class Shopper {
        -String address
    }

    class OrderCart {
        -String orderId
        -Shopper shopper
        -List~Item~ cartItems
        +getTotalAmount() double
    }

    MainServer --> SystemFacade
    SystemFacade --> Store
    Store "1" *-- "*" Item : contains
    Store "1" *-- "*" OrderCart : manages
    Item <|-- Meal
    Item <|-- BeverageItem
    Person <|-- Shopper
    OrderCart "1" *-- "1" Shopper : belongs to
    OrderCart "1" o-- "*" Item : includes
```

## 2. Database ER Diagram (`database-diagram.png` equivalent)

```mermaid
erDiagram
    USERS ||--o{ ORDERS : places
    ORDERS ||--|{ ORDER_ITEMS : contains
    MENU_ITEMS ||--o{ ORDER_ITEMS : "included in"

    USERS {
        int id PK
        string username
        string password
        string email
        string role
    }

    MENU_ITEMS {
        int id PK
        string name
        string description
        decimal price
        string category
    }

    ORDERS {
        string order_id PK
        int user_id FK
        decimal total_amount
        datetime created_at
        string status
    }

    ORDER_ITEMS {
        int id PK
        string order_id FK
        int menu_item_id FK
        int quantity
        decimal unit_price
    }
```
