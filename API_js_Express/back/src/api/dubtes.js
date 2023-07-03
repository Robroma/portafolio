const express = require("express");
const router = express.Router();
const db = require("../db/db");

router.get("/dubtes", async (req, res) => {
    try {
        db.query(
            `
        SELECT d.*, u.nom AS nomAlumne, COUNT(DISTINCT r.id) AS numRespostas, COUNT(DISTINCT l.id) AS numLikes
        FROM dubtes AS d
        JOIN assignaturas AS a ON d.id_assignatura = a.id
        JOIN classes AS c ON a.id_classe = c.id
        LEFT JOIN usuaris AS u ON d.id_usuari = u.usuID
        LEFT JOIN respostas AS r ON d.id = r.id_dubte
        LEFT JOIN likes AS l ON d.id = l.id_dubte
        WHERE c.nom = ?
        GROUP BY d.id;
        
      `,
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


router.get("/dubteId", async (req, res) => {
    try {
        db.query(
            `
      SELECT d.*, u.nom AS nomAlumne, COUNT(r.id) AS numRespostas
      FROM dubtes AS d
      JOIN assignaturas AS a ON d.id_assignatura = a.id
      JOIN classes AS c ON a.id_classe = c.id
      LEFT JOIN usuaris AS u ON d.id_usuari = u.usuID
      LEFT JOIN respostas AS r ON d.id = r.id_dubte
      WHERE d.id = ?
      GROUP BY d.id
      `,
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


router.put("/insertDubtes", (req, res) => {
    const {
        idUsuari,
        texto_pregunta,
        titulo_pregunta,
        foto,
        id_assignatura,
        public,
        uf,
    } = req.body;
    const query = `
      INSERT INTO dubtes 
      (id_usuari, texto_pregunta, titulo_pregunta, foto, fecha, likes, id_assignatura, public, uf)
      VALUES (?, ?, ?, ?, CURDATE(), 0, ?, ?, ?);
    `;
    db.query(
        query,
        [
            idUsuari,
            texto_pregunta,
            titulo_pregunta,
            foto,
            id_assignatura,
            public,
            uf,
        ],
        (err, results) => {
            if (err) {
                console.log("Error al insertar datos:", err);
                res.status(500).json({ error: "Error al insertar datos" });
            } else {
                res.json({ message: "Inserción exitosa", results });
            }
        }
    );
});