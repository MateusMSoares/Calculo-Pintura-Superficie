// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Index from './components/index';
import PaginaConfiguracao from './components/Configuracao';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/configuracao" element={<PaginaConfiguracao />} />
      </Routes>
    </Router>
  );
}

export default App;
