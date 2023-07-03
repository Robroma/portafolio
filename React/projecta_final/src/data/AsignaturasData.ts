import { IAsignatura } from "@/models/IAsignatura";
import axios from "axios";
//cambiar por la base de datos


export const getAsignaturasData = async (): Promise<IAsignatura[]> => {
  try {
    const response = await axios.post('http://localhost:3002/api/assignaturas', {
      nom: "wiam2"
    });
    
    return response.data; // asumimos que la respuesta es un array de IAsignatura
  } catch (error) {
    console.error(error);
    return []; // Retorna un array vacío en caso de error
  }
}
