POST:localhost:8080/register 

{
"username": "john_doe",
"passwordHash": "random_password_hash",
"email": "john.doe@example.com",
"role": "user"
}

POST: localhost:8080/auth/login

{
"password": "random_password_hash",
"email": "john.doe@example.com"
}
