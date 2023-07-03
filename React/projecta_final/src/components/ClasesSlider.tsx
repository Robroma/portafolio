import { getAsignaturasData } from "@/data/AsignaturasData";
import { getPreguntasData } from "@/data/PreguntasData";
import Pregunta from "@/components/Pregunta";
import { useState, useEffect } from "react";
import { IAsignatura } from "@/models/IAsignatura";
import { IPregunta } from "@/models/IPregunta";
import Asignaturas from "./Asignaturas/Asignaturas";
import Router from "next/router"

function ClasesSlider() {
  const [AsignaturasData, setAsignaturasData] = useState<IAsignatura[]>([]);
  const [PreguntasData, setPreguntasData] = useState<IPregunta[]>([]);
  const [selectedAssignatura, setSelectedAssignatura] = useState<number>(0);

  useEffect(() => {
    async function fetchData() {
      const asignaturas = await getAsignaturasData();
      const preguntas = await getPreguntasData();
      setAsignaturasData(asignaturas);
      setPreguntasData(preguntas);
      setSelectedAssignatura(1);
    }
    fetchData();
  }, []);

  if (!AsignaturasData.length) {
    return <p>Cargando...</p>; 
  }

  return (
    <>
      <Asignaturas
        selectedAssignatura={selectedAssignatura}
        setSelectedAssignatura={setSelectedAssignatura}
      />
      <button className="afegirPregunta" onClick={()=> Router.push("/CrearDubte")}>+</button>
      {PreguntasData.filter(
        (pregunta) => pregunta.id_assignatura === selectedAssignatura
      ).map((pregunta, index) => (
        <Pregunta key={index} pregunta={pregunta} />
      ))}
    </>
  );
}

export default ClasesSlider;
