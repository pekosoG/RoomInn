module.exports={
  "development": {
    "username": "root",
    "password": "root",
    "database": "roomin_api",
    "host": "localhost",
    "dialect": "mysql"
  },
  "test": {
    "username": "root",
    "password": null,
    "database": "database_test",
    "host": "127.0.0.1",
    "dialect": "mysql"
  },
  "production": {
    "username": process.env.CLEARDB_DATABASE_USER,
    "password": process.env.CLEARDB_DATABASE_PASS,
    "database": process.env.CLEARDB_DATABASE,
    "host": process.env.CLEARDB_DATABASE_URL,
    "dialect": "mysql"
  }
}