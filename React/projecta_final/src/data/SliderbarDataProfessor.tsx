import React, { ReactNode } from "react";
import * as FaIcons from "react-icons/fa";
import * as AiIcons from "react-icons/ai";
import * as IoIcons from "react-icons/io";
import { IsubNav } from "../models/ISubNav";
//cambiar por la base de datos

export const SidebarDataProfessor: IsubNav[] = [
  {
    title: "Home",
    path: "/",
    icon: <AiIcons.AiFillHome />,
  },

  {
    title: "Aula",
    path: "/Professor/AulasProfesor",
    icon: <IoIcons.IoMdHelpCircle />,
  }
  
];
