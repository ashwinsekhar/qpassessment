# Getting Started

To get started,

1. Get the project and run docker-compose up -d on the root folder qp-assessment.
2. After successfully running the App please use the following urls for operations mentioned below -
3. For Admin specific functions I have created a separate controller with prefixing "api/admin" to all the URLs. This can then be used in SecurityConfig to check the ADMIN role and require you to provide credentials for the admin before accessing the URL.
4. For User specific functions I have created a separate controller with prefixing "api/user" to all the URLs. This can then be used in SecurityConfig to check the USER role and require you to provide credentials for the user before accessing the URL.

# Calling the APIs

Admin URLs -

1.  Add new grocery items to the system - "/api/admin/add".
2.  View existing grocery items - "/api/admin/groceries".
3.  Remove grocery items from the system - "/api/admin/deleteByIds".
4.  Update details (e.g., name, price) of existing grocery items - "/api/admin/updateDetails".

5.  Manage inventory levels of grocery items - "/api/admin/updateInventory".

User URLs -

1. View the list of available grocery items - "/api/admin/view".

2. Ability to book multiple grocery items in a single order - "/api/admin/order".

Please refer to the controller class for examples for the request body for POST APIs.
