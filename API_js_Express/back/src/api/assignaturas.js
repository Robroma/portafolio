const express = require("express");
const router = express.Router();
const db = require("../db/db");

router.get("/assignaturas", async (req, res) => {
    try {
      db.query(
        `SELECT * FROM assignaturas WHERE id_classe = (SELECT id FROM classes WHERE nom = ?)`,
        [req.body.nom],
        (error, results) => {
          if (error) {
            console.log("Error al obtener datos:", error);
            res.status(500).json({ error: "Error al obtener datos" });
          } else {
            res.json(results);
          }
        }
      );
    } catch (error) {
      console.log(error);
      res.status(500).json({ error: "Error al obtener datos" });
    }
  });

