const express = require("express");
const router = express.Router();
const db = require("../db/db");

router.get("/datos", async (req, res) => {
    try {
      // Lógica para obtener datos desde la base de datos
      db.query("SELECT * FROM usuaris", (error, results) => {
        if (error) {
          console.log("Error al obtener datos:", error);
          res.status(500).json({ error: "Error al obtener datos" });
        } else {
          res.json(results);
        }
      });
    } catch (error) {
      console.log(error);
      res.status(500).json({ error: "Error al obtener datos" });
    }
  });

  router.put("/insertUsuari", (req, res) => {
    const checkQuery = `
    INSERT INTO usuaris
    (usuID, nom, cognoms, rol, classe)
    VALUES(?, ?, ?, ?, ?);
    `;
  
    db.query(
      checkQuery,
      [
        req.body.usuID,
        req.body.nom,
        req.body.cognoms,
        req.body.rol,
        req.body.classe,
      ],
      (err, results) => {
        if (err) {
          console.log("Error al verificar los datos:", err);
          res.status(500).json({ error: "Error al verificar los datos" });
        } else {
          res.json(results);
        }
      }
    );
  });