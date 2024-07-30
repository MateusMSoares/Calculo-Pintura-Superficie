// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import PaintCalculator from './components/Index';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<PaintCalculator />} />
      </Routes>
    </Router>
  );
}

export default App;
