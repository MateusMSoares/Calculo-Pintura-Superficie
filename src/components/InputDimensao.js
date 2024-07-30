import React from 'react';

const unidadeMedida = 'metros';
const DimensionsInput = ({ surfaceType, surfaceDimensions, setSurfaceDimensions }) => (
    <div className="form-group">
        {surfaceType === 'cone' && (
            <>
                <input 
                    type="number" 
                    placeholder={`Raio da Base (${unidadeMedida})`}
                    value={surfaceDimensions.dimensao1} 
                    onChange={(e) => setSurfaceDimensions({ ...surfaceDimensions, dimensao1: e.target.value })}
                />
                <input 
                    type="number" 
                    placeholder={`Altura (${unidadeMedida})`} 
                    value={surfaceDimensions.dimensao2} 
                    onChange={(e) => setSurfaceDimensions({ ...surfaceDimensions, dimensao2: e.target.value })}
                />
            </>
        )}
        {surfaceType === 'circulo' && (
            <input 
                type="number" 
                placeholder={`Raio (${unidadeMedida})`} 
                value={surfaceDimensions.dimensao1} 
                onChange={(e) => setSurfaceDimensions({ ...surfaceDimensions, dimensao1: e.target.value })}
            />
        )}
        {surfaceType === 'quadrado' && (
            <input 
                type="number" 
                placeholder={`Lado (${unidadeMedida})`} 
                value={surfaceDimensions.dimensao1} 
                onChange={(e) => setSurfaceDimensions({ ...surfaceDimensions, dimensao1: e.target.value })}
            />
        )}
        {surfaceType === 'retangulo' && (
            <>
                <input 
                    type="number" 
                    placeholder={`Altura (${unidadeMedida})`} 
                    value={surfaceDimensions.dimensao1} 
                    onChange={(e) => setSurfaceDimensions({ ...surfaceDimensions, dimensao1: e.target.value })}
                />
                <input 
                    type="number" 
                    placeholder={`Largura (${unidadeMedida})`} 
                    value={surfaceDimensions.dimensao2} 
                    onChange={(e) => setSurfaceDimensions({ ...surfaceDimensions, dimensao2: e.target.value })}
                />
            </>
        )}
    </div>
);

export default DimensionsInput;