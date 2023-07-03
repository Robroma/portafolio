import Asignaturas from "@/components/Asignaturas/Asignaturas";
import Sidebar from "@/components/Sidebar/Sidebar";
import { useState } from "react";
import axios from "axios";
import { useSession } from "next-auth/react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Router from "next/router";

function CrearDubte() {
  const { data: session } = useSession();
  const [selectedAssignatura, setSelectedAssignatura] = useState<number>(1);
  const [textoPregunta, setTextoPregunta] = useState<string>("");
  const [tituloPregunta, setTituloPregunta] = useState<string>("");
  const [uf, setUf] = useState<string>("");
  const [publico, setPublico] = useState<number>(0);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:3002/api/insertDubtes", {
        idUsuari: session?.user?.name,
        texto_pregunta: textoPregunta,
        titulo_pregunta: tituloPregunta,
        foto: "", // Debes manejar la foto según tu caso
        id_assignatura: selectedAssignatura,
        public: publico,
        uf: uf,
      });
      // Reset form
      setTextoPregunta("");
      setTituloPregunta("");
      setUf("");
      setPublico(0);
      setSelectedAssignatura(1);
      toast.success("Dubte creat");
      setTimeout(() => {
        Router.push("/");
      }, 3000);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <Sidebar />
      <div className="home">
        <Asignaturas
          selectedAssignatura={selectedAssignatura}
          setSelectedAssignatura={setSelectedAssignatura}
        />
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            value={tituloPregunta}
            onChange={(e) => setTituloPregunta(e.target.value)}
            placeholder="Título pregunta"
          />
          <input
            type="text"
            value={uf}
            onChange={(e) => setUf(e.target.value)}
            placeholder="UF"
          />
          <textarea
          className="textarea"
            value={textoPregunta}
            onChange={(e) => setTextoPregunta(e.target.value)}
            placeholder="Texto pregunta"
          />
          <div>
            <label>
              <input
                type="radio"
                value="1"
                checked={publico === 1}
                onChange={() => setPublico(1)}
              />
              Público
            </label>
            <label>
              <input
                type="radio"
                value="0"
                checked={publico === 0}
                onChange={() => setPublico(0)}
              />
              No Público
            </label>
          </div>
          <button type="submit">Enviar</button>
        </form>
        <ToastContainer />
      </div>
    </>
  );
}

export default CrearDubte;
