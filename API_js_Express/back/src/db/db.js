const mysql = require('mysql2');

// Crea una conexión a la base de datos
const connection = mysql.createConnection({
  host: 'proxy.inf-edt.org',
  port: 5508,
  user: 'root',
  password: 'jupiter',
  database: 'dam2RobertJaume',
  ssl: false,
  
  //charset: 'utf8mb4',
  timezone: 'UTC',
  connectTimeout: 300000 // tiempo de espera de 30 segundos
});

// Conecta a la base de datos
connection.connect((error) => {
  if (error) {
    console.error('Error al conectar a la base de datos:', error);
  } else {
    console.log('Conexión exitosa a la base de datos');
  }
});

module.exports = connection;
