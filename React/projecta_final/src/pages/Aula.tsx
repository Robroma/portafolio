import Sidebar from "@/components/Sidebar";
import { NextPageContext } from "next";
import { getSession, useSession } from "next-auth/react";
import Asignaturas from "@/components/Asignaturas/Asignaturas";
import { useState, useEffect } from "react";
import * as BsIcons from "react-icons/bs";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export async function getServerSideProps(context: NextPageContext) {
  const session = await getSession(context);

  if (!session) {
    return {
      redirect: {
        destination: "/Login",
        permanent: false,
      },
    };
  }

  return {
    props: {},
  };
}

export default function Aula() {
  const { data: session } = useSession();
  const [selectedAssignatura, setSelectedAssignatura] = useState<number>(1);
  const [aulaUsers, setAulaUsers] = useState<any[]>([]);

  console.log(session);

  const fetchAulaUsers = async () => {
    try {
      const response = await axios.get(
        `http://localhost:3002/api/aulaAlumneList/${selectedAssignatura}`
      );

      const data = response.data;
      setAulaUsers(data.results);
    } catch (error) {
      console.log(error);
    }
  };

  const insertAula = async () => {
    try {
      await axios.post("http://localhost:3002/api/aulaAlumne", {
        idUsuari: session?.user?.name,
        idAula: selectedAssignatura,
      });

      fetchAulaUsers();
    } catch (error) {
      toast.error("Ya estás en la cola");
    }
  };

  useEffect(() => {
    fetchAulaUsers();
    const intervalId = setInterval(fetchAulaUsers, 5000);
    return () => clearInterval(intervalId);
  }, [selectedAssignatura]);

  return (
    <div>
      <Sidebar />
      <div className="home">
        <Asignaturas
          selectedAssignatura={selectedAssignatura}
          setSelectedAssignatura={setSelectedAssignatura}
        />

        <button onClick={insertAula}>
          <BsIcons.BsHandIndex className="iconPregunta" />
        </button>

        <ul>
          {"Número de persones en cua: "+aulaUsers.length}
          <br/> 
          {aulaUsers.map((user, index) => (
            (user.id_usuari === session?.user?.name) ?
            <p key={index}>{"Lloc a la cua: "+(index+1)}</p> : null
          ))}
          
        </ul>

        <ToastContainer />
      </div>
    </div>
  );
}
