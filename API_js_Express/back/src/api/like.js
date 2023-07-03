const express = require("express");
const router = express.Router();
const db = require("../db/db");

router.post("/like", (req, res) => {
    const { idUsuari, idDubte } = req.body;
  
    const checkQuery = `
      SELECT COUNT(*) as count FROM likes WHERE id_usuari = ? AND id_dubte = ?
    `;
  
    db.query(checkQuery, [idUsuari, idDubte], (err, results) => {
      if (err) {
        console.log("Error al verificar los datos:", err);
        res.status(500).json({ error: "Error al verificar los datos" });
      } else if (results[0].count > 0) {
        const deleteQuery = `
          DELETE FROM likes WHERE id_usuari = ? AND id_dubte = ?
        `;
  
        db.query(deleteQuery, [idUsuari, idDubte], (err, results) => {
          if (err) {
            console.log("Error al eliminar el like:", err);
            res.status(500).json({ error: "Error al eliminar el like" });
          } else {
            res.json({ message: "Like eliminado exitosamente", liked: false });
          }
        });
      } else {
        const insertQuery = `
          INSERT INTO likes (id_usuari, id_dubte) VALUES (?, ?)
        `;
  
        db.query(insertQuery, [idUsuari, idDubte], (err, results) => {
          if (err) {
            console.log("Error al insertar el like:", err);
            res.status(500).json({ error: "Error al insertar el like" });
          } else {
            res.json({ message: "Like insertado exitosamente", liked: true });
          }
        });
      }
    });
  });

  router.get("/like/:idUsuari/:idDubte", (req, res) => {
    const { idUsuari, idDubte } = req.params;
  
    const checkQuery = `
      SELECT COUNT(*) as count FROM likes WHERE id_usuari = ? AND id_dubte = ?
    `;
  
    db.query(checkQuery, [idUsuari, idDubte], (err, results) => {
      if (err) {
        console.log("Error al verificar los datos:", err);
        res.status(500).json({ error: "Error al verificar los datos" });
      } else {
        res.json({ liked: results[0].count > 0 });
      }
    });
  });