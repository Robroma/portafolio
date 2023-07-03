import { IPregunta } from "@/models/IPregunta";
import axios from "axios";

export const getPreguntaIdData = async (
  id: string
): Promise<IPregunta[]> => {
  try {
    const response = await axios.post("http://localhost:3002/api/dubteId", {
      id: id,
    });

    return response.data; // asumimos que la respuesta es un array de IAsignatura
  } catch (error) {
    console.error(error);
    return []; // Retorna un array vacío en caso de error
  }
};
