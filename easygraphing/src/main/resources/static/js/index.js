import * as THREE from 'three';
import { OrbitControls } from 'three/addons/controls/OrbitControls.js';
import { TextGeometry } from 'three/addons/geometries/TextGeometry.js';
import { FontLoader } from 'three/addons/loaders/FontLoader.js';
import { Reflector } from 'three/addons/objects/Reflector';

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

    const hemiLight = new THREE.AmbientLight( 0xffffff, 0.5 );
    hemiLight.position.set( 0, 20, 0 );
    scene.add( hemiLight );

    const dirLight = new THREE.DirectionalLight( 0xa0a0a0, 2 );
    dirLight.angle=Math.PI/2;
    dirLight.position.set( 0, 10, 10 );
    dirLight.castShadow = true;
    dirLight.shadow.camera.top = 20;
    dirLight.shadow.camera.bottom = - 20;
    dirLight.shadow.camera.left = - 20;
    dirLight.shadow.camera.right = 20;
    dirLight.shadow.mapSize.height = 512;
    dirLight.shadow.mapSize.width = 512;

    scene.add( dirLight );

    // set background
    scene.background = new THREE.Color( 0xe2e2e2 );
    scene.fog = new THREE.Fog( 0xe2e2e2, 10,80 );
    let mirrorGeometry = new THREE.PlaneBufferGeometry( 100, 1000, 100, 1000 );
    let mirror = new Reflector( mirrorGeometry, {
        clipBias: 0.01,
        textureWidth: window.innerWidth * window.devicePixelRatio,
        textureHeight: window.innerHeight * window.devicePixelRatio,
        color: 0x777777,
        opacity: 0.1
    } );
    mirror.position.y = 0
    mirror.rotateX(  - Math.PI / 2 );
    scene.add( mirror );
    // set bottom
    // const mesh = new THREE.Mesh(
    //     new THREE.PlaneGeometry( 500, 500 ),
    //     new THREE.MeshStandardMaterial({
    //         blur: [300,100],
    //         resolution:2048,
    //         roughness:0,
    //         metalness:0.5,
    //         color: 'white'
    //     })
    // )
    // mesh.rotation.x = - Math.PI / 2;
    // mesh.receiveShadow = true;
    // scene.add( mesh );

    // set grid
    // const gridHelper = new THREE.GridHelper( 100, 100 );
    // scene.add( gridHelper );

    makeGraph(1,10, scene);
    makeGraph(2,5, scene);
    makeGraph(3,8, scene);

    makePieGraph([
        { value: 30, color: 0xFF5733 },
        { value: 20, color: 0x33FF57 },
        { value: 50, color: 0x5733FF }
    ],scene)

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
    let fontLoader = new FontLoader();
    const url = "../font/helvetiker_regular.typeface.json";
    fontLoader.load(url, (font) => {
        let geometry = new TextGeometry(
            value.toString(),
            {
                font: font,
                size: 0.5,
                height: 0,
                curveSegments: 12,
                width: 50
            }
        );
        geometry.computeBoundingBox();
        let xMid = -0.5 * ( geometry.boundingBox.max.x - geometry.boundingBox.min.x );
        geometry.translate( xMid, 0, 0 );
        let material = new THREE.MeshBasicMaterial({
            color: 0x000000,
        });
        let mesh = new THREE.Mesh(geometry, material);
        mesh.position.set(index*1.3, value+0.2, 0);
        scene.add(mesh);

    });
}

function makePieGraph(data,scene){
    var totalValue = data.reduce((sum, segment) => sum + segment.value, 0);
    var currentAngle = -Math.PI / 2;

    data.forEach(segment => {
        var geometry = new THREE.CylinderGeometry(1, 1, 0.2, 64, 1, false, currentAngle, Math.PI * 2 * (segment.value / totalValue));
        var material = new THREE.MeshBasicMaterial({ color: segment.color, side: THREE.DoubleSide });
        var pieSegment = new THREE.Mesh(geometry, material);
        scene.add(pieSegment);

        currentAngle += Math.PI * 2 * (segment.value / totalValue);
    });
}

init()

