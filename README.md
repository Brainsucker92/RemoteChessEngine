# Remote Chess Engine (RCE)

### What is this project?

This project allows chess enthusiasts to run a chess engine on a powerful remote machine and transfers the engine's
evaluation results to a (less) powerful device (e.g. cell phone or laptop).

__Please note: This project is in a very early phase of development. It may contain bugs, errors, unexpected behavior or
lack of functionality.__
Nevertheless it is completely safe to use this software, as long as you follow the instructions in the security notes.
As always for Open-Source projects: Don't trust the code. Check the code yourself and run if you consider it being safe.

### How does it work?

**RCE** consists out of two parts: A server (**RCES**) and a client (**RCEC**). The server generally runs on the more
powerful machine, providing quick and deep analysis for any client.

The client can generally be any device, but most likely it will be a device with fairly low computing power (cell phone,
laptop, Raspberry Pi, Arduino, etc.). The client may run a chess GUI, which will display computing results to the user.
Other use-cases, like automatic analysis of certain positions or games are also thinkable.

## Remote Chess Engine Server (RCES)

In order to run the server, you will have to provide a command string that will be used to launch a new process of the
chess engine. This command string is highly dependant from your local host machine infrastructure (operating system,
installation path, etc.)
Therefore, we cannot provide a general value for your particular use-case.

Once a connection has been established by any client device, **RCES** will launch a fresh chess engine process, using
the command string you provided. Every client will get a completely fresh engine process he can communicate with. Every
engine process is completely independent of each other. They do not interfere in any way with each other except for the
shared hardware resources.

By default, **RCES** runs on port **42042**.

## Remote Chess Engine Client (RCEC)

**RCEC** is used in order to connect to any **RCES** service you want to use.
**RCEC** receives protocol commands from your local chess GUI and forwards those commands to the **RCES**.
**RCEC** also receives evaluation results from the **RCES** and forwards this information to the chess GUI.

### Prerequisites

In order to run both **RCES** and **RCEC** a minimum of Java JRE 11 is required on your host system.

**RCES** also requires an installation of any chess engine you intend to use. Any engine that supports a text-based
protocol (UCI or CECP) should work as intended. There is **NO** native chess engine included in the **RCES**
installation.

### Security notes

The server accepts every incoming connection requests without any additional layer of security or authorization.
Therefore, any device on the same network may use this service without any kind of authorization.

**It is highly recommended DO NOT directly forward the port of RCES on your local router towards the world wide web
unless you know exactly what you're doing.**
If you wish to access your local **RCES** installation from the internet, consider using a VPN connection to your local
infrastructure. This provides an additional layer of security.

#### Support this project

If you want to support further development of this project, you can do so by donating any amount using one of the
following options:

[Bitcoin](bitcoin:BC1Q6R4374UUS59V0SNAAJ7DE93LY7S78HM7ECJ9P2?label=Remote%20Chess%20Engine%20Project%20Support&message=Thank%20you%20very%20much%21):
BC1Q6R4374UUS59V0SNAAJ7DE93LY7S78HM7ECJ9P2

[Dogecoin](dogecoin:DHUFG1Q2kt8nqZWJUHgXCtVRnEaownLNjQ?label=Remote%20Chess%20Engine%20Project%20Support&message=Thank%20you%20very%20much%21):
DHUFG1Q2kt8nqZWJUHgXCtVRnEaownLNjQ

I am very grateful for any support you can give. Donating is NOT mandatory.

Project Launch Date: Mar '22
