import { IPregunta } from "@/models/IPregunta";
import { AiOutlineLike } from "react-icons/ai";
import { FaRegComment } from "react-icons/fa";
import Router from "next/router";
import { useDispatch } from 'react-redux';
import {setPregunta} from "@/data/actions";
import { useState, useEffect } from "react";
import axios from "axios";
import { useSession } from "next-auth/react";


function Pregunta({ pregunta }: { pregunta: IPregunta }) {
  const { data: session } = useSession();
  const dispatch = useDispatch();
  const [numLikes, setNumLikes] = useState(pregunta.numLikes);
  const [isLiked, setIsLiked] = useState(false);
  
  useEffect(() => {
    const fetchLikeStatus = async () => {
      try {
        const response = await axios.get(`http://localhost:3002/api/like/${session?.user?.name}/${pregunta.id}`);
        setIsLiked(response.data.liked);
      } catch (error) {
        console.log(error);
      }
    };

    fetchLikeStatus();
  }, []);
  const handleClickLike = async () => {
    try {
      const response = await axios.post(`http://localhost:3002/api/like`, {
        idUsuari: session?.user?.name,
        idDubte: pregunta.id
      });
      setIsLiked(response.data.liked);
      setNumLikes(isLiked ? Number(numLikes) - 1 : Number(numLikes) + 1);
    } catch (error) {
      console.log(error);
    }
  };

  const handleClick = () => {
    dispatch(setPregunta(pregunta));
    Router.push(`/preguntas/${pregunta.id}`);
  };


  return (
    <div className="pregunta">
      <h4>
        <span>{pregunta.nomAlumne}</span>
        <span>{pregunta.fecha.split("T")[0]}</span>
      </h4> 
      <p
        title="Haz clic para ver detalles"
        onClick={handleClick}
      >
        {pregunta.uf}
        <br />
        {pregunta.titulo_pregunta}
      </p>
      <h4>
        <button
          onClick={handleClick}
        >
          {`${pregunta.numRespostas} `}
          <FaRegComment className="iconPregunta" />
        </button>
        <button className={isLiked ? "btnLike" : "btnNotLike"} onClick={handleClickLike}>
          {numLikes}
          <AiOutlineLike color={isLiked ? "#1b6bb8" : "black"} className="iconPregunta" />
        </button>
      </h4>
    </div>
  );
}

export default Pregunta;

