const express = require("express");
const router = express.Router();
const db = require("../db/db");

router.post("/aulaAlumne", async (req, res) => {
    const { idUsuari, idAula } = req.body;
  
    try {
      // Primero, verifica si existe un registro con el mismo id_usuari y estat en 0
      const checkRows = `SELECT COUNT(*) as count FROM aula WHERE id_usuari = ? AND estat = 0 AND id_aula = ?`;
      db.query(checkRows, [idUsuari, idAula], (error, results) => {
        if (error) {
          console.log("Error al verificar los datos:", error);
          res.status(500).json({ error: "Error al verificar los datos" });
        } else if (results[0].count > 0) {
          // Si existe un registro con el mismo id_usuari y estat en 0, envía un error
          res
            .status(400)
            .json({
              error: "Ya existe un registro con el mismo id_usuari y estat en 0",
            });
        } else {
          // Si no existe tal registro, procede a insertar los datos
          const insertQuery = `INSERT INTO aula (id_usuari, consulta , id_aula) VALUES (?, CURRENT_TIMESTAMP, ?)`;
          db.query(insertQuery, [idUsuari, idAula], (error, results) => {
            if (error) {
              console.log("Error al insertar datos:", error);
              res.status(500).json({ error: "Error al insertar datos" });
            } else {
              res.json({ message: "Inserción exitosa", results });
            }
          });
        }
      });
    } catch (error) {
      console.log("Error inesperado:", error);
      res.status(500).json({ error: "Error inesperado" });
    }
  });

  router.get("/aulaAlumneList/:idAula", async (req, res) => {
    const idAula = req.params.idAula;
    try {
      db.query(
        `
        SELECT a.*, u.nom 
        FROM aula AS a 
        JOIN usuaris as u ON a.id_usuari = u.usuID
        WHERE estat = 0 AND id_aula = ? 
        ORDER BY id
        `,
        [idAula],
        (error, results) => {
          if (error) {
            console.log("Error al obtener datos:", error);
            res.status(500).json({ error: "Error al obtener datos" });
          } else {
            res.json({ message: "Obtención exitosa", results });
          }
        }
      );
    } catch (error) {
      console.log(error);
      res.status(500).json({ error: "Error al obtener datos" });
    }
  });

  router.post("/aulaInsertAtencio", (req, res) => {
    const checkQuery = `
      SELECT atencio FROM aula 
      WHERE id = ?;
    `;
  
    db.query(checkQuery, [req.body.id], (err, results) => {
      if (err) {
        console.log("Error al verificar datos:", err);
        res.status(500).json({ error: "Error al verificar datos" });
      } else if (results[0].atencio !== null) {
        res.status(400).json({ error: "La atención ya ha sido registrada." });
      } else {
        const query = `
          UPDATE aula 
          SET atencio = CURRENT_TIMESTAMP()
          WHERE id = ?;
        `;
        db.query(query, [req.body.id], (err, results) => {
          if (err) {
            console.log("Error al insertar datos:", err);
            res.status(500).json({ error: "Error al insertar datos" });
          } else {
            res.json({ message: "Inserción exitosa", results });
          }
        });
      }
    });
  });

  router.post("/aulaInsertResposta", (req, res) => {
    const checkQuery = `
      SELECT atencio, resposta FROM aula 
      WHERE id = ?;
    `;
  
    db.query(checkQuery, [req.body.id], (err, results) => {
      if (err) {
        console.log("Error al verificar datos:", err);
        res.status(500).json({ error: "Error al verificar datos" });
      } else if (results[0].atencio === null || results[0].resposta !== null) {
        res
          .status(400)
          .json({
            error:
              "No se puede responder hasta que se preste atención y no se haya respondido antes.",
          });
      } else {
        const query = `
          UPDATE aula 
          SET resposta = CURRENT_TIMESTAMP(), estat = 1
          WHERE id = ?;
        `;
        db.query(query, [req.body.id], (err, results) => {
          if (err) {
            console.log("Error al insertar datos:", err);
            res.status(500).json({ error: "Error al insertar datos" });
          } else {
            res.json({ message: "Inserción exitosa", results });
          }
        });
      }
    });
  });