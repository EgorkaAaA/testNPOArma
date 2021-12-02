# testNPOArma

Models:
Users
-user_id long
-login String
-password String
-banned boolean

Post: /users/add?login=user_login&password=user_password - Endpoint for add new User

Put: /users/{id}/update?login=user_login4&password=user_password - Endpoint for update user

Patch: /users/{id}/ban - Endpoint for ban user

Get: /auth?login=user_name&password=user_password - Endpoint for authntithicate user
