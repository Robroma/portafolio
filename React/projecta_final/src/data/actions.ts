import { IPregunta } from "@/models/IPregunta";

export const SET_PREGUNTA = 'SET_PREGUNTA';

interface SetPreguntaAction {
  type: typeof SET_PREGUNTA,
  payload: IPregunta,
}

export const setPregunta = (pregunta: IPregunta): SetPreguntaAction => ({
  type: SET_PREGUNTA,
  payload: pregunta,
});

export type PreguntaActionTypes = SetPreguntaAction;