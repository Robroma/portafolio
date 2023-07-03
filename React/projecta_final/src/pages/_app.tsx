import "@/styles/globals.css";
import React from "react";
import type { AppProps } from "next/app";
import "@/styles/globals.css";
import { SessionProvider } from "next-auth/react";
import { Provider } from "react-redux";
import { createWrapper } from "next-redux-wrapper";
import store from "../../store/store";

function App({ Component, pageProps }: AppProps) {
  return (
    <Provider store={store}>
      <SessionProvider>
        <Component {...pageProps} />
      </SessionProvider>
    </Provider>
  );
}
const makestore = () => store;
const wrapper = createWrapper(makestore);

export default wrapper.withRedux(App);
