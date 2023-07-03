import React, { useState } from "react";
import * as FaIcons from "react-icons/fa";
import * as AiIcons from "react-icons/ai";
import { SidebarData } from "../../data/SliderbarData";
import { SidebarDataProfessor } from "../../data/SliderbarDataProfessor";
import Router from "next/router";
import { signOut } from "next-auth/react";
import * as SlIcons from "react-icons/sl";
import { useSession } from "next-auth/react";
import styles from './sidebar.module.css'; // Importar el archivo CSS con CSS Modules

const Sidebar = () => {
  const { data: session, status } = useSession();
  const sidebarData =
    session && session?.user?.image === "inf"
      ? SidebarDataProfessor
      : SidebarData;

  const [sidebar, setSidebar] = useState(false);

  const showSidebar = () => setSidebar(!sidebar);

  return (
    <>
      <div className="navbar">
        <p className="menu-bars">
          <FaIcons.FaBars onClick={showSidebar} />
        </p>
      </div>
      <nav className={sidebar ? "nav-menu active" : "nav-menu"}>
        <ul className="nav-menu-items" onClick={showSidebar}>
          <li className="navbar-toggle">
            <p className="menu-bars">
              <AiIcons.AiOutlineClose />
            </p>
          </li>
          {sidebarData.map((item, index) => (
            <li key={index} onClick={() => Router.push(item.path)}>
              <button className="btnSidebar">
                {item.icon}
                <span className="sidebarText">{item.title}</span>
              </button>
            </li>
          ))}
          <li key={"logOut"} onClick={() => signOut()}>
            <button className="btnSidebar">
              {<SlIcons.SlLogout />}
              <span className="sidebarText">{"LogOut"}</span>
            </button>
          </li>
        </ul>
      </nav>
    </>
  );
};

export default Sidebar

// return (
//     <>
//       <div className={styles.navbar}>
//         <p className={styles.menu_bars}>
//           <FaIcons.FaBars onClick={showSidebar} />
//         </p>
//       </div>
//       <nav className={sidebar ? styles.nav_menu_active : styles.nav_menu}>
//         <ul className={styles.nav_menu_items} onClick={showSidebar}>
//           <li className={styles.navbar_toggle}>
//             <p className={styles.menu_bars}>
//               <AiIcons.AiOutlineClose />
//             </p>
//           </li>
//           {sidebarData.map((item, index) => (
//             <li key={index} onClick={() => Router.push(item.path)}>
//               <button className={styles.btnSidebar}>
//                 {item.icon}
//                 <span className={styles.sidebarText}>{item.title}</span>
//               </button>
//             </li>
//           ))}
//           <li key={"logOut"} onClick={() => signOut()}>
//             <button className={styles.btnSidebar}>
//               {<SlIcons.SlLogout />}
//               <span className={styles.sidebarText}>{"LogOut"}</span>
//             </button>
//           </li>
//         </ul>
//       </nav>
//     </>
//   );
// };