import Login from "@/components/Login";
import { getSession, signIn } from "next-auth/react";
import { NextPageContext } from "next";
import { useRouter } from "next/router";
import { useCallback, useState } from "react";
import styles from '@/styles/login.module.css'; // Importar el archivo CSS con CSS Modules


export async function getServerSideProps(context: NextPageContext) {
  const session = await getSession(context);

  if (session) {
    return {
      redirect: {
        destination: "/",
        permanent: false,
      },
    };
  }

  return {
    props: {},
  };
}

async function login(username: string, password: string){
  const response = await fetch('http://localhost:3001/api/login',{
    method:'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({username,password}),
  });

  if(response.ok){
    const {token} = await response.json();
    localStorage.setItem('token',token);
  } else {
    const { error } = await response.json();
    console.log(error);
  }

}

export default Login;