import ClasesSlider from "@/components/ClasesSlider";
import Sidebar from "@/components/Sidebar";
import { NextPageContext } from "next";
import { getSession,useSession } from "next-auth/react";
import axios from "axios";
import { useEffect } from "react";

export async function getServerSideProps(context: NextPageContext) {

  const session = await getSession(context);

  if (!session) {
    return {
      redirect: {
        destination: "/Login",
        permanent: false,
      },
    };
  } else {
      
  }

  return {
    props: {},
  };
}

const handleSubmit = async (session2: any) => {
  try {
    const usu = session2.user.name;
    const nom = session2?.user?.email?.split(" ")[0];
    const cognoms = session2?.user?.email?.split(" ").slice(1).join(" ");
    const rol = session2?.user?.image === "inf" ? 1 : 0;

console.log(usu);
    await axios.post("http://localhost:3002/api/insertUsuari", {
        usuID: usu,
        nom: nom,
        cognoms: cognoms,
        rol: rol, 
        classe: session2?.user?.image, 
      });
      console.log("asdasd")
    
  } catch (error) {
    console.error(error);
  }
};

export default function Home() {

  const  { data: session2 } =  useSession();

  useEffect(()=>{
    if(session2){
      handleSubmit(session2)
    }
  })


  return (
    <div>
      <Sidebar />
      <div className="home">
        <ClasesSlider />
      </div>
    </div>
  );
}
