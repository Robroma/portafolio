import React, { ReactNode } from "react";
import * as FaIcons from "react-icons/fa";
import * as AiIcons from "react-icons/ai";
import * as IoIcons from "react-icons/io";
import * as SlIcons from "react-icons/sl"
import * as RiIcons from "react-icons/ri";
import { IsubNav } from "../models/ISubNav";
//cambiar por la base de datos

export const SidebarData: IsubNav[] = [
  {
    title: "Home",
    path: "/",
    icon: <AiIcons.AiFillHome />,
  },
  {
    title: "Aula",
    path: "/Aula",
    icon: <IoIcons.IoMdHelpCircle />,
  },
  
];
