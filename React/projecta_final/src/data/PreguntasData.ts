import { IPregunta } from "@/models/IPregunta";
import axios from "axios";
//cambiar por la base de datos


export const getPreguntasData = async (): Promise<IPregunta[]> => {
  try {
    const response = await axios.post('http://localhost:3002/api/dubtes', {
      nom: "wiam2",
    });
    
    return response.data; // asumimos que la respuesta es un array de IPregunta
  } catch (error) {
    console.error(error);
    return []; // Retorna un array vacío en caso de error
  }
}
