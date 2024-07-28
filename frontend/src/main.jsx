import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { MasterProvider } from './context/MasterProvider.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  // <React.StrictMode>
    <MasterProvider>
      <App />
    </MasterProvider>
  // </React.StrictMode>,
)
