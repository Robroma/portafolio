import { createStore, combineReducers} from 'redux';
import { IPregunta } from "@/models/IPregunta";
import preguntaReducer from '@/data/reducer';
// otros reducers...

const rootReducer = combineReducers({
  pregunta: preguntaReducer,
  // otros reducers...
});

// Estado inicial
const initialState = {
  counter: 0,
  pregunta: {} as IPregunta
}

// Tipos de acciones
type Action = 
  | { type: 'INCREMENT' }
  | { type: 'SET_PREGUNTA', pregunta: IPregunta }

// Reducer
const reducer = (state = initialState, action: Action) => {
  switch (action.type) {
    case 'INCREMENT':
      return { ...state, counter: state.counter + 1 };
    case 'SET_PREGUNTA':
      return { ...state, pregunta: action.pregunta };
    default:
      return state;
  }
};

// Store
//const store = createStore(reducer);

//export type RootState = ReturnType<typeof reducer>; 

//export default store;

const store = createStore(rootReducer);

export type RootState = ReturnType<typeof rootReducer>; 

export default store;
