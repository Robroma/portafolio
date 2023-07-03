const express = require('express');
const path = require('path');
const cors = require('cors');
const { exec } = require('child_process');
const jwt = require('jsonwebtoken');

// Crea una instancia de la aplicación Express
const app = express();

// Configura middleware para el manejo de solicitudes JSON
app.use(express.json());
app.use(cors({
  origin: 'http://localhost:3000'
}))

app.use('/api', require('./api/assignaturas'));
app.use('/api', require('./api/aula'));
app.use('/api', require('./api/dubtes'));
app.use('/api', require('./api/like'));
app.use('/api', require('./api/respostas'));
app.use('/api', require('./api/usuaris'));

app.post('/login', (req, res) => {
  exec(`php ./src/public/authLDAP.php "${req.body.username}" "${req.body.password}"`, (error, stdout, stderr) => {
    if (error) {
      console.error(`Error al ejecutar el archivo PHP: ${error}`);
      res.status(500).send('Error interno del servidor');
      return;
    }
    // Procesar la respuesta del archivo PHP
    res.send(stdout);
  });
});

// Configura una ruta de prueba
app.get('/prueba', (req, res) => {
  res.send('¡Backend funcionando!');
});

// Inicia el servidor y escucha en un puerto específico
const port = 3002; 
app.listen(port,() => {
  console.log(`Servidor corriendo en el puerto ${port}`);
});
