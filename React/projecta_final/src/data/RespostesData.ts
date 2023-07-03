import { IResposta } from "@/models/IResposta";

import axios from "axios";

export const getRespostesData = async (id_dubte:string): Promise<IResposta[]> => {
  try {
    const response = await axios.post("http://localhost:3002/api/respostas", { 
      id: id_dubte
    });
    
    return response.data; 
  } catch (error) {
    console.error(error);
    return []; 
  }
}
