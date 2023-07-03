import { IAsignatura } from "@/models/IAsignatura";
import { useEffect, useState } from "react";
import { getAsignaturasData } from "@/data/AsignaturasData";

interface AsignaturasProps {
  selectedAssignatura: number;
  setSelectedAssignatura: (id: number) => void;
}

const Asignaturas: React.FC<AsignaturasProps> = ({ selectedAssignatura, setSelectedAssignatura }) => {
  const [AsignaturasData, setAsignaturasData] = useState<IAsignatura[]>([]);

  useEffect(() => {
    async function fetchData() {
      const asignaturas = await getAsignaturasData();
      setAsignaturasData(asignaturas);
      if (asignaturas.length > 0) {
        setSelectedAssignatura(asignaturas[0].id); // set the first item's id as the selectedAssignatura
      }
    }
    fetchData();
  }, []);

  if (!AsignaturasData.length) {
    return <p>Cargando...</p>;
  }

  return (
    <select
      value={selectedAssignatura}
      onChange={(e) => setSelectedAssignatura(Number(e.target.value))}
      className="selectClase"
    >
      {AsignaturasData.map((item, index) => (
        <option key={item.nom} value={item.id}>
          {item.nom}
        </option>
      ))}
    </select>
  );
};

export default Asignaturas;


