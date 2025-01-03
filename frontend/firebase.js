const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

// src/firebase.js
import firebase from 'firebase/app';
import 'firebase/firestore'; // Caso você use o Firestore
import 'firebase/auth'; // Caso você use a autenticação

const firebaseConfig = {
    apiKey: "AIzaSyA2wC8Wcp9_3RhY2EcJV10PSaoxbBXeVIw",
    authDomain: "calculo-pintura.firebaseapp.com",
    projectId: "calculo-pintura",
    storageBucket: "calculo-pintura.firebasestorage.app",
    messagingSenderId: "265859385688",
    appId: "1:265859385688:web:0e6543a4c404cc205f7d2d",
    measurementId: "G-LDJ1YSZW82"
};

firebase.initializeApp(firebaseConfig);

export const db = firebase.firestore();
export const auth = firebase.auth();
export default firebase;
