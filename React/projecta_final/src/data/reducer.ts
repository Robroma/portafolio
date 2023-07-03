import { IPregunta } from "@/models/IPregunta";
import { PreguntaActionTypes, SET_PREGUNTA } from './actions';

interface PreguntaState {
  pregunta: IPregunta | null,
}

const initialState: PreguntaState = {
  pregunta: null,
};

export default function reducer(state = initialState, action: PreguntaActionTypes): PreguntaState {
  switch (action.type) {
    case SET_PREGUNTA:
      return { ...state, pregunta: action.payload };
    default:
      return state;
  }
}