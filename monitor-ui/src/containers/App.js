import React, { useEffect, useState } from "react"
import socketIOClient from "socket.io-client"
import styled from 'styled-components';
import * as Func from '../utils/AppFunc';
import { config } from '../config';

import logo from "./logo.svg"
import "./App.css"

const apiServiceEndpoint = config.apiServiceEndpoint;

const ConnectStyle = styled.span`
  background: green;
  padding: 5px;
  color: white;
`;

const DisConnectStyle = styled.span`
  background: red;
  padding: 5px;
`;

const init = async () => {
  await Func.initLoadData();
}
const handleApi = async () => {
  await Func.uploadMember();
  await Func.uploadRedeem();
}

const App = () => {
  const [client, setClient] = useState('');
  const [connect, setConnect] = useState(false);
  const [time, setTime] = useState(0);

  useEffect(() => {
    const socket = socketIOClient(apiServiceEndpoint, { transports: ['websocket'] })
    socket.on("create_redeem", async data => {
      await Func.saveRedeemLocal(JSON.parse(data))
    })

    socket.on("create_member", async data => {
      await Func.saveMemberLocal(JSON.parse(data))
    })

    socket.on("update_member", async data => {
      init();
    })

    socket.on("client_id", (id)=>{
      setClient('Your id: ' + id);
      setTime(new Date());
      setConnect(true)

      // call init first time only
      init();
    })
    socket.on("client_close", (status)=>{
      setConnect(status);
    })

    socket.on("timeSync", action => {
      handleApi();
    })
    
    socket.on('error', ()=>{
      setConnect(false);
    })

    socket.on('connect_error', err => {
      setConnect(false)
    });
    socket.on('connect_failed', err => {
      setConnect(false)
    });
    socket.on('disconnect', err => {
      setConnect(false)
    });
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div style={{ marginBottom: "20px", color: "chocolate" }}>
          {client}
        </div>
        <div>
            {connect ? <ConnectStyle>Connected.</ConnectStyle>:<DisConnectStyle>Disconnect</DisConnectStyle>}
        </div>
        <div className="DivButton">
          <button onClick={() => handleApi()} className="Button">
            Refresh Sync API Service<br />
            {apiServiceEndpoint}
          </button>
        </div>
        <div>Time at: {time && time.toLocaleString()}</div>
      </header>
    </div>
  )
}

export default App
