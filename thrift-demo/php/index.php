<?php

error_reporting(E_ALL);

// BAD: 写死了路径
require_once '/usr/lib/php/Thrift/ClassLoader/ThriftClassLoader.php';

use Thrift\ClassLoader\ThriftClassLoader;

$GEN_DIR = dirname(__FILE__) . '/gen-php';

$loader = new ThriftClassLoader();
$loader->registerNamespace('Thrift', '/usr/lib/php');
$loader->registerDefinition('Hello', $GEN_DIR);
$loader->register();

if (php_sapi_name() == 'cli') {
  ini_set("display_errors", "stderr");
}

use Thrift\Protocol\TBinaryProtocol;
use Thrift\Transport\TPhpStream;
use Thrift\Transport\TBufferedTransport;

class HelloHandler implements \Hello\HelloServiceIf {
    public function ping() {
        error_log("ping()");
    }

    public function hello($name) {
        return "Hello $name";
    }

    public function helloV2(\Hello\Person $person) {
        return "Hello {$person->firstName}, {$person->lastName}";
    }

};

header('Content-Type', 'application/x-thrift');
if (php_sapi_name() == 'cli') {
    echo "\r\n";
}

$handler = new HelloHandler();
$processor = new \Hello\HelloServiceProcessor($handler);

$transport = new TBufferedTransport(new TPhpStream(TPhpStream::MODE_R | TPhpStream::MODE_W));
$protocol = new TBinaryProtocol($transport, true, true);

$transport->open();
$processor->process($protocol, $protocol);
$transport->close();
