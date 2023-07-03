const express = require("express");
const router = express.Router();
const db = require("../db/db");
const multer = require("multer");
const storage = multer.memoryStorage();
const upload = multer({ storage: storage });

router.get("/respostas", async (req, res) => {
    try {
      db.query(
        "SELECT respostas.*, usuaris.nom  FROM respostas JOIN usuaris ON respostas.id_usuari = usuaris.usuID   WHERE respostas.id_dubte = ?      ",
        [req.body.id],
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

  router.put("/respostasInsert", upload.single("foto"), async (req, res) => {
    try {
      db.query(
        "INSERT INTO respostas (id_usuari, texto_resposta, foto, fecha, id_dubte, likes) VALUES (?, ?, ?, CURDATE(), ?, 0)",
        [
          req.body.id_usuari,
          req.body.texto_resposta,
          req.file,
          req.body.id_dubte,
        ],
        (error, results) => {
          if (error) {
            console.log("Error al insertar datos:", error);
            res.status(500).json({ error: "Error al insertar datos" });
          } else {
            res.json(results);
          }
        }
      );
    } catch (error) {
      console.log(error);
      res.status(500).json({ error: "Error al insertar datos" });
    }
  });