import Sidebar from "@/components/Sidebar";
import { getSession, useSession } from "next-auth/react";
import Asignaturas from "@/components/Asignaturas/Asignaturas";
import { useState, useEffect } from "react";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { NextPageContext } from "next";


export async function getServerSideProps(context: NextPageContext) {
  const session = await getSession(context);

  if (!session || session?.user?.image !== "inf") {
    return {
      redirect: {
        destination: "/Login",
        permanent: false,
      },
    };
  }

  return {
    props: {robert:true},
  };
}

export default function AulaProfesor() {
  const { data: session } = useSession();
  const [selectedAssignatura, setSelectedAssignatura] = useState<number>(1);
  const [aulaUsers, setAulaUsers] = useState<any[]>([]);

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

  const insertAtencio = async (id: string) => {
    try {
      await axios.post("http://localhost:3002/api/aulaInsertAtencio", {
        id: id,
      });

      fetchAulaUsers();
      toast.success("Atenent");
    } catch (error) {
      toast.error("El usuario ya esta siendo atendido");
      console.log(error);
    }
  };

  const insertResposta = async (id: string) => {
    try {
      await axios.post("http://localhost:3002/api/aulaInsertResposta", {
        id: id,
      });

      fetchAulaUsers();
      toast.success("Alumne respost");
    } catch (error) {
      toast.error("Primero tienes que dar atencion");
      console.log(error);
    }
  };

  console.log(aulaUsers)

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

        <ul className="liAula">
          {aulaUsers
            .filter((user) => user.estat === 0)
            .sort((a, b) => a.id - b.id)
            .map((user) => (
              <li className="liAula" key={user.id}>
                {user.nom}
                <button className="btnAula" onClick={() => insertAtencio(user.id)}>Atencio</button>
                <button className="btnAula" onClick={() => insertResposta(user.id)}>
                  Resposta
                </button>
              </li>
            ))}
        </ul>

        <ToastContainer />
      </div>
    </div>
  );
}
