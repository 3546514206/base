// 基础场景设置
let scene = new THREE.Scene();
let camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
let renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.getElementById('container').appendChild(renderer.domElement);

// 创建星星
function createStars() {
    let starGeometry = new THREE.BufferGeometry();
    let starMaterial = new THREE.PointsMaterial({color: 0xffffff});

    // 创建存储星星位置的数组
    let starVertices = [];
    for (let i = 0; i < 10000; i++) {
        let x = (Math.random() - 0.5) * 2000;
        let y = (Math.random() - 0.5) * 2000;
        let z = (Math.random() - 0.5) * 2000;
        starVertices.push(x, y, z);
    }

    // 将星星的位置数据存储为 Float32Array
    starGeometry.setAttribute('position', new THREE.Float32BufferAttribute(starVertices, 3));

    let stars = new THREE.Points(starGeometry, starMaterial);
    scene.add(stars);
}

// 调整相机位置
camera.position.z = 500;

// 创建银河系星星
createStars();

// 渲染循环
function animate() {
    requestAnimationFrame(animate);

    // 星星缓慢旋转
    scene.rotation.x += 0.0005;
    scene.rotation.y += 0.0005;

    renderer.render(scene, camera);
}

// 窗口调整时更新渲染器大小
window.addEventListener('resize', function () {
    let width = window.innerWidth;
    let height = window.innerHeight;
    renderer.setSize(width, height);
    camera.aspect = width / height;
    camera.updateProjectionMatrix();
});

animate();
