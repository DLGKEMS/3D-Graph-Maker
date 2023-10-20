import * as THREE from 'three';
import { OrbitControls } from 'three/addons/controls/OrbitControls.js';
import { TextGeometry } from 'three/addons/geometries/TextGeometry.js';

const fontSize = 5;

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
    // graph
    var geom = new THREE.BoxBufferGeometry();
    var material = new THREE.ShaderMaterial({
        uniforms: {
            thickness: {
                value: 1
            },
            color: {
                value: new THREE.Color()
            }
        },

        vertexShader: vertexShader,
        fragmentShader: fragmentShader,
        extensions: {derivatives: true},
    });

    const cube = new THREE.Mesh( geom, material );
    cube.material.uniforms.color.value.set(Math.random() * 0xffffff);
    console.log(cube)
    cube.scale.y = value;
    cube.position.set(index*1.3, value/2, 0);
    scene.add( cube );

    // text
    const textGeometry = new TextGeometry( 'Hello three.js!', {
        size: 80,
        height: 5,
        curveSegments: 12,
        bevelEnabled: true,
        bevelThickness: 10,
        bevelSize: 8,
        bevelOffset: 0,
        bevelSegments: 5
    } );
    const textMaterial = new THREE.MeshBasicMaterial( { color: 0xb2b2b2 } );
    const text = new THREE.Mesh( textGeometry, textMaterial );


    text.position.z = 0;

    text.rotation.x = 0;
    text.rotation.y = Math.PI * 2;

    scene.add(text);

}
init()

