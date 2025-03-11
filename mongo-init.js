db.createUser(
        {
            user: "bookstore",
            pwd: "example",
            roles: [
                {
                    role: "readWrite",
                    db: "bookstore"
                }
            ]
        }
);