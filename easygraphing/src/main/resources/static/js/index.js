import * as THREE from 'three';
import { OrbitControls } from 'three/addons/controls/OrbitControls.js';

function init(){
    const renderer = new THREE.WebGLRenderer();
    const canvasBox=document.getElementById('canvas-box');
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );
    const controls = new OrbitControls( camera, renderer.domElement );

    // set renderer
    renderer.setSize( canvasBox.offsetWidth, canvasBox.offsetHeight );
    canvasBox.appendChild( renderer.domElement );

    // set camera
    camera.position.set( 10, 10, 10 );
    controls.update();

    // set background
    scene.background= new THREE.Color(0xb2b2b2);

    // set grid
    const gridHelper = new THREE.GridHelper( 100, 100 );
    scene.add( gridHelper );

    makeGraph(1,10, scene);
    makeGraph(2,5, scene);
    makeGraph(3,8, scene);
    // orbitcontrol을 사용하기 위해서는 애니메이션이 실행되어야 한다.(boiler)
    animate()
    function animate() {
        requestAnimationFrame( animate );
        renderer.render(scene, camera);
        controls.update();
    }
}

function makeGraph(index, value, scene){
    const geometry = new THREE.BoxGeometry( 1, value, 1 );
    const material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
    const cube = new THREE.Mesh( geometry, material );
    cube.position.x=index;
    cube.position.y=value/2;
    scene.add( cube );
}
init()

