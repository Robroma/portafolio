import { NextPageContext } from "next";
import { getSession } from "next-auth/react";

import Seguimiento from '../../components/Seguimiento'

export async function getServerSideProps(context: NextPageContext) {
  const session = await getSession(context);

  if (!session || session?.user?.image !== "inf") {
    return {
      redirect: {
        destination: "/Login",
        permanent: false,
      },
    };
  }

  return {
    props: {robert:true},
  };
}

export default Seguimiento;
