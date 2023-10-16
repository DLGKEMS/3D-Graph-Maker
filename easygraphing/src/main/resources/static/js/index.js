import * as THREE from 'three';
import { OrbitControls } from 'three/addons/controls/OrbitControls.js';

const renderer = new THREE.WebGLRenderer();
const canvasBox=document.getElementById('canvas-box');
renderer.setSize( canvasBox.offsetWidth, canvasBox.offsetHeight );
canvasBox.appendChild( renderer.domElement );

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );
const controls = new OrbitControls( camera, renderer.domElement );

camera.position.set( 10, 10, 10 );
controls.update();


makeGraph(1,10, scene);

function makeGraph(index, value, scene){
    const geometry = new THREE.BoxGeometry( 1, value, 1 );
    const material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
    const cube = new THREE.Mesh( geometry, material );
    scene.add( cube );
}


function animate() {
    requestAnimationFrame( animate );
    renderer.render(scene, camera);
    controls.update();
}
animate()

renderer.render( scene, camera );
