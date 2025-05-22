// services/api.js
import axios from 'axios';

// IMPORTANTE: Substitua pelo endereço IP da sua máquina na rede local
// Use o IP da sua máquina em vez de localhost para acessar de dispositivos físicos
// Para descobrir seu IP no Windows, abra o Prompt de Comando e digite 'ipconfig'
// Procure por 'Endereço IPv4' na sua conexão de rede ativa

// ALTERE ESTA LINHA com o IP da sua máquina e a porta do seu Spring Boot
const API_URL = 'http://10.105.81.168:8080';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  },
});

export default api;
export const planetService = {
  // Obter todos os planetas
  getAllPlanets: async () => {
    try {
      const response = await api.get('/planetas');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar planetas:', error);
      throw error;
    }
  },
  
  // Obter um planeta pelo ID
  getPlanetById: async (id) => {
    try {
      const response = await api.get(`/planetas/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao buscar planeta ${id}:`, error);
      throw error;
    }
  },
  
  // Criar um novo planeta
  createPlanet: async (planetData) => {
    try {
      const response = await api.post('/planetas', planetData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar planeta:', error);
      throw error;
    }
  },
  
  // Atualizar um planeta existente
  updatePlanet: async (id, planetData) => {
    try {
      const response = await api.put(`/planetas/${id}`, planetData);
      return response.data;
    } catch (error) {
      console.error(`Erro ao atualizar planeta ${id}:`, error);
      throw error;
    }
  },
  
  // Excluir um planeta
  deletePlanet: async (id) => {
    try {
      const response = await api.delete(`/planetas/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao excluir planeta ${id}:`, error);
      throw error;
    }
  }
};
