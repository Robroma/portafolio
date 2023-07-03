import Sidebar from "@/components/Sidebar";
import { useState, useEffect, useRef } from "react";
import { getSession } from "next-auth/react";
import { NextPageContext } from "next";
import { useRouter } from "next/router";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/store";
import { IResposta } from "@/models/IResposta";
import { getRespostesData } from "@/data/RespostesData";
import axios from "axios";
import { IPregunta } from "@/models/IPregunta";
import { getPreguntaIdData } from "@/data/PreguntaIdData";
import { useSession } from "next-auth/react";

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

interface AnswerInputProps {
  onSubmit: (answer: string, image: File, code: string) => void;
}

function PreguntaPage({ onSubmit }: AnswerInputProps) {
  const [Pregunta, setPregunta] = useState<IPregunta[]>([]);
  const [loading, setLoading] = useState(true);
  const [answer, setAnswer] = useState("");
  const [code, setCode] = useState("");
  const [respuestas, setRespuestas] = useState<IResposta[]>([]);
  const { data: session } = useSession();
  const router = useRouter();
  const { id } = router.query;


  useEffect(() => {
    async function fetchData() {
      setLoading(true);
      if (id !== undefined) {
        const idString = id.toString();
        const preg = await getPreguntaIdData(idString);
        setPregunta(preg);

        if (preg[0] !== undefined && preg[0].id !== undefined) {
          const resps = await getRespostesData(preg[0].id.toString());
          setRespuestas(resps);
        }
      }
      setLoading(false);
    }
    fetchData();
  }, [id]);


  const handleAnswerSubmit = async () => {
    try {

      const response = await axios.post(
        "http://localhost:3002/api/respostasInsert",
        {
          id_usuari: session?.user?.name,
          texto_resposta: answer,
          foto: null,
          id_dubte: Pregunta[0].id,
        }
      );
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const tabulacions = `\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0`;

  const handleAnswerChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setAnswer(e.target.value);
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!Pregunta) {
    return <div>Pregunta no encontrada</div>;
  }

  return (
    <>
      <Sidebar />
      {Pregunta ? (
        <div className="preguntaDintre">
          <h1>{Pregunta[0].titulo_pregunta}</h1>
          <p>{`Dia: ${
            Pregunta[0].fecha.split("T")[0]
          } ${tabulacions} Uf: ${
            Pregunta[0].uf
          } ${tabulacions} Alumne: ${Pregunta[0].nomAlumne}`}</p>
          <p>{Pregunta[0].texto_pregunta}</p>

          <div className="answer-input">
            <textarea
              className="answer-input__answer"
              placeholder="Escribe tu respuesta aquí..."
              value={answer}
              onChange={handleAnswerChange}
            />
            <button
              className="answer-input__submit"
              onClick={handleAnswerSubmit}
            >
              Enviar respuesta
            </button>
          </div>
          <div>
            <h1>Respuestas</h1>
            {respuestas.map((respuesta) => (
              <div key={respuesta.id}>
                <p>Usuario: {respuesta.nom}</p>
                <p>Fecha: {respuesta.fecha.split("T")[0]}</p>
                <p>Respuesta: {respuesta.texto_resposta}</p>
              </div>
            ))}
          </div>
        </div>
      ) : (
        <div>Pregunta no encontrada</div>
      )}
    </>
  );
}

export default PreguntaPage;
